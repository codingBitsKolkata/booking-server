package com.orastays.booking.bookingserver.helper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.dao.BookingVsPaymentDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.dao.CancellationDAO;
import com.orastays.booking.bookingserver.dao.CancellationVsRoomDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
import com.orastays.booking.bookingserver.entity.CancellationEntity;
import com.orastays.booking.bookingserver.entity.CancellationVsRoomEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.cashfree.RefundModel;
import com.orastays.booking.bookingserver.service.BookingVsPaymentService;
import com.orastays.booking.bookingserver.service.BookingVsRoomService;
import com.orastays.booking.bookingserver.serviceImpl.NotifyServiceImpl;

@Component
public class RoomUpdateAfterGatewayPayment {
	private static final Logger logger = LogManager.getLogger(NotifyServiceImpl.class);

	@Autowired
	protected BookingVsPaymentService bookingVsPaymentService;

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;

	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;

	@Autowired
	protected BookingVsRoomService bookingVsRoomService;

	@Autowired
	protected CashFreeApi cashFreeApi;
	
	@Autowired
	protected CancellationDAO cancellationDAO;
	
	@Autowired
	protected CancellationVsRoomDAO cancellationVsRoomDAO;
	
	public synchronized void checkRoomStatusAndBookOrRefund(Map<String, String> paramMap) {
		if (logger.isInfoEnabled()) {
			logger.info("checkRoomStatusAndBookOrRefund -- START");
		}

		List<BookingVsRoomEntity> bookingVsRoomEntities = new CopyOnWriteArrayList<>();

		BookingVsPaymentEntity bookingVsPaymentEntity = bookingVsPaymentService
				.getBookingVsPaymentEntityByOrderId(paramMap.get("orderId"));

		bookingVsPaymentEntity.setTxStatus(paramMap.get("txStatus"));
		bookingVsPaymentEntity.setReferenceId(paramMap.get("referenceId"));
		bookingVsPaymentEntity.setPaymentMode(paramMap.get("paymentMode"));
		bookingVsPaymentEntity.setTxTime(paramMap.get("txTime"));
		bookingVsPaymentEntity.setTxMsg(paramMap.get("txMsg"));
		bookingVsPaymentEntity.setSignature(paramMap.get("signature"));
		bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
		bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());

		// bookingVsPaymentEntity.setStatus(PaymentStatus.CANCELLED.ordinal()); set
		// status as cancelled if room booked.
		// txstatus will be success but status will be cancelled and refund will be
		// initiated

		// get the booking entity
		BookingEntity bookingEntity = bookingDAO.find(bookingVsPaymentEntity.getBookingEntity().getBookingId());

		// check room status in a synchronized way for private and shared
		bookingEntity.getBookingVsRoomEntities().parallelStream().forEach(room -> {
			// for private room check numberOfCot exists or not for that booking
			// for shared room call property server

			if (room.getNumOfCot() != null && Integer.parseInt(room.getNumOfCot()) > 0) { // private room
				if (bookingVsRoomService.checkIfPrivateRoomIsBooked(room.getRoomId())) {
					// room is booked
					bookingVsRoomEntities.add(room);

				}
			} else { // shared room call property server

			}
		});

		if (bookingVsRoomEntities.size() > 0) {
			// initiate refund and set cash part to cancelled
			initiateRefund(bookingEntity, bookingVsPaymentEntity);
		} else {
			if (Double.parseDouble(bookingVsPaymentEntity.getOrderAmount()) == Double
					.parseDouble((paramMap.get("orderAmount")))) {
				// update room
				bookingEntity.getBookingVsRoomEntities().forEach(room -> {
					// update room
					room.setModifiedBy(room.getCreatedBy());
					room.setModifiedDate(Util.getCurrentDateTime());
					room.setStatus(RoomStatus.BOOKED.ordinal());

					bookingVsRoomDAO.update(room);
				});

				// update booking vs payment
				bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
				bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());
				bookingVsPaymentEntity.setStatus(PaymentStatus.COMPLETED.ordinal());

				bookingVsPaymentDAO.update(bookingVsPaymentEntity);

				// update booking master
				bookingEntity.setModifiedBy(bookingEntity.getCreatedBy());
				bookingEntity.setModifiedDate(Util.getCurrentDateTime());
				bookingEntity.setStatus(BookingStatus.BOOKED.ordinal());

				bookingDAO.update(bookingEntity);
			} else {
				logger.info("customer changed the order amount");
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("checkRoomStatusAndBookOrRefund -- END");
		}
	}
	
	//initiate refund and update booking master, bookingVsRoom, bookingVsPayment and cancellation
	public void initiateRefund(BookingEntity bookingEntity, BookingVsPaymentEntity bookingVsPaymentEntity) {
		bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
		bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setStatus(PaymentStatus.CANCELLED.ordinal());
		
		try {
			RefundModel refundModel = cashFreeApi.initiateRefund(bookingEntity, bookingVsPaymentEntity, bookingVsPaymentEntity.getOrderAmount(), AuthConstant.REFUND_CONCURRENT_BOOKING_NOTE);
			if(refundModel.getStatus().equalsIgnoreCase(AuthConstant.CASHFREE_OK)) {
				
				CancellationEntity cancellationEntity = new CancellationEntity();
				cancellationEntity.setCreatedBy(bookingEntity.getCreatedBy());
				cancellationEntity.setCreatedDate(Util.getCurrentDateTime());
				cancellationEntity.setStatus(Status.INACTIVE.ordinal());
				cancellationEntity.setReasonForCancellation(AuthConstant.REFUND_CONCURRENT_BOOKING_NOTE);
				cancellationEntity.setTotalAmountPaid(bookingVsPaymentEntity.getOrderAmount());
				cancellationEntity.setTotalAmountRefunded(bookingVsPaymentEntity.getAmountPaid());
				cancellationEntity.setTotalPaybleWithoutGst(bookingEntity.getTotalPaybleWithoutGST());
				cancellationEntity.setBookingEntity(bookingEntity);
				cancellationEntity.setUserId(String.valueOf(bookingVsPaymentEntity.getCreatedBy()));
				
				Long id = (Long) cancellationDAO.save(cancellationEntity);
				CancellationEntity cancellationEntity2 = cancellationDAO.find(id);
				
				bookingEntity.getBookingVsRoomEntities().parallelStream().forEach(room -> {
					room.setModifiedBy(room.getCreatedBy());
					room.setModifiedDate(Util.getCurrentDateTime());
					room.setStatus(RoomStatus.INACTIVE.ordinal());
					bookingVsRoomDAO.update(room);
					
					//insert into cancellation vs room
					CancellationVsRoomEntity cancellationVsRoomEntity = new CancellationVsRoomEntity();
					cancellationVsRoomEntity.setCreatedBy(bookingEntity.getCreatedBy());
					cancellationVsRoomEntity.setCreatedDate(Util.getCurrentDateTime());
					cancellationVsRoomEntity.setStatus(Status.INACTIVE.ordinal());
					cancellationVsRoomEntity.setBookingVsRoomEntity(room);
					cancellationVsRoomEntity.setCancellationEntity(cancellationEntity2);
					
					cancellationVsRoomDAO.save(cancellationVsRoomEntity);
				});
				
				//update booking entity
				bookingEntity.setModifiedBy(bookingEntity.getCreatedBy());
				bookingEntity.setModifiedDate(Util.getCurrentDateTime());
				bookingEntity.setStatus(BookingStatus.CANCELLED.ordinal());
				bookingDAO.update(bookingEntity);
				
				//update booking vs payment entity
				bookingVsPaymentDAO.update(bookingVsPaymentEntity);
				
				bookingEntity.getBookingVsPaymentEntities().forEach(bpe -> {
					if (bpe.getGatewayEntity().getGatewayId() != bookingVsPaymentEntity.getGatewayEntity().getGatewayId()) {
						// cash payment checking

						bpe.setModifiedBy(bpe.getCreatedBy());
						bpe.setModifiedDate(Util.getCurrentDateTime());
						bpe.setStatus(PaymentStatus.CANCELLED.ordinal());
						bookingVsPaymentDAO.update(bpe);
					}
				});
				//insert into cancellation
			} else {
				//refund not done
			}
		} catch (FormExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
