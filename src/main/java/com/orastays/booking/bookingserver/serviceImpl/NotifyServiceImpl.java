package com.orastays.booking.bookingserver.serviceImpl;

import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.dao.BookingVsPaymentDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.dao.CancellationDAO;
import com.orastays.booking.bookingserver.dao.CancellationVsRoomDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;
import com.orastays.booking.bookingserver.entity.CancellationEntity;
import com.orastays.booking.bookingserver.entity.CancellationVsRoomEntity;
import com.orastays.booking.bookingserver.helper.AuthConstant;
import com.orastays.booking.bookingserver.helper.BookingStatus;
import com.orastays.booking.bookingserver.helper.PaymentStatus;
import com.orastays.booking.bookingserver.helper.RoomStatus;
import com.orastays.booking.bookingserver.helper.RoomUpdateAfterGatewayPayment;
import com.orastays.booking.bookingserver.helper.Status;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.service.BookingVsPaymentService;
import com.orastays.booking.bookingserver.service.NotifyService;

@Transactional
@Service
public class NotifyServiceImpl implements NotifyService {
	private static final Logger logger = LogManager.getLogger(NotifyServiceImpl.class);

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;

	@Autowired
	protected BookingVsPaymentService bookingVsPaymentService;

	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;

	@Autowired
	protected CancellationDAO cancellationDAO;

	@Autowired
	protected CancellationVsRoomDAO cancellationVsRoomDAO;
	
	@Autowired
	protected RoomUpdateAfterGatewayPayment roomUpdateAfterGatewayPayment;

	@Override
	public void updateBookingStatus(Map<String, String> paramMap) {
		if (logger.isInfoEnabled()) {
			logger.info("updateBookingStatus -- START");
		}

		if (paramMap.get("txStatus").equals("SUCCESS")) {
			// validate booking and initiate refund if already room booked
			roomUpdateAfterGatewayPayment.checkRoomStatusAndBookOrRefund(paramMap);

		} else if (paramMap.get("txStatus").equals("FLAGGED")) {

		} else if (paramMap.get("txStatus").equals("PENDING")) {

		} else if (paramMap.get("txStatus").equals("FAILED")) { // successful but kept on hold by risk system
			updateBookingOnFaliureOrCancelled(paramMap);
		} else if (paramMap.get("txStatus").equals("CANCELLED")) {
			updateBookingOnFaliureOrCancelled(paramMap);
		}

		if (logger.isInfoEnabled()) {
			logger.info("updateBookingStatus -- END");
		}

	}

	public void updateBookingOnFaliureOrCancelled(Map<String, String> paramMap) {
		if (logger.isInfoEnabled()) {
			logger.info("updateBookingOnFaliureOrCancelled -- START");
		}

		BookingVsPaymentEntity bookingVsPaymentEntity = bookingVsPaymentService
				.getBookingVsPaymentEntityByOrderId(paramMap.get("orderId"));

		bookingVsPaymentEntity.setTxStatus(paramMap.get("txStatus"));
		bookingVsPaymentEntity.setReferenceId(paramMap.get("referenceId"));
		bookingVsPaymentEntity.setPaymentMode(paramMap.get("paymentMode"));
		bookingVsPaymentEntity.setTxTime(paramMap.get("txTime"));
		bookingVsPaymentEntity.setTxMsg(paramMap.get("txMsg"));
		// while fetching check cancelled status and txStatus for failed and cancelled
		bookingVsPaymentEntity.setStatus(PaymentStatus.CANCELLED.ordinal());
		bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
		bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());
		try {
			// update booking vs payment

			bookingVsPaymentDAO.update(bookingVsPaymentEntity);

			// update booking

			BookingEntity bookingEntity = bookingDAO.find(bookingVsPaymentEntity.getBookingEntity().getBookingId());

			bookingEntity.setModifiedBy(bookingEntity.getCreatedBy());
			bookingEntity.setModifiedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.CANCELLED.ordinal());

			bookingDAO.update(bookingEntity);

			//insert into cancellation
			CancellationEntity cancellationEntity = new CancellationEntity();
			cancellationEntity.setCreatedBy(bookingEntity.getCreatedBy());
			cancellationEntity.setCreatedDate(Util.getCurrentDateTime());
			cancellationEntity.setStatus(Status.INACTIVE.ordinal());
			cancellationEntity.setReasonForCancellation(AuthConstant.USER_CANCELLED_BOOKING_IN_GATEWAY);
			cancellationEntity.setTotalAmountPaid(bookingVsPaymentEntity.getOrderAmount());
			cancellationEntity.setTotalAmountRefunded(bookingVsPaymentEntity.getAmountPaid());
			cancellationEntity.setTotalPaybleWithoutGst(bookingEntity.getTotalPaybleWithoutGST());
			cancellationEntity.setBookingEntity(bookingEntity);
			cancellationEntity.setUserId(String.valueOf(bookingVsPaymentEntity.getCreatedBy()));
			
			Long id = (Long) cancellationDAO.save(cancellationEntity);
			CancellationEntity cancellationEntity2 = cancellationDAO.find(id);
			
			// update booking vs room

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
			
			// for partial payment the cash payment status also needs to be set to cancelled
			bookingEntity.getBookingVsPaymentEntities().forEach(bpe -> {
				// there can be max 2 rows for a payment. One for cash, one for cashless
				// if cash then only update else update will be done in the section update
				// booking vs payment

				if (bpe.getGatewayEntity().getGatewayId() != bookingVsPaymentEntity.getGatewayEntity().getGatewayId()) {
					// cash payment checking

					bpe.setModifiedBy(bpe.getCreatedBy());
					bpe.setModifiedDate(Util.getCurrentDateTime());
					bpe.setStatus(PaymentStatus.CANCELLED.ordinal());
					bookingVsPaymentDAO.update(bpe);
				}
			});


		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("updateBookingOnFaliureOrCancelled -- END");
		}
	}
}
