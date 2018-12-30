package com.orastays.booking.bookingserver.helper;

import java.util.LinkedHashMap;
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
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
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
		bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
		bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());
		
		//bookingVsPaymentEntity.setStatus(PaymentStatus.CANCELLED.ordinal()); set status as cancelled if room booked.
		//txstatus will be success but status will be cancelled and refund will be initiated
		
		//get the booking entity
		BookingEntity bookingEntity = bookingDAO.find(bookingVsPaymentEntity.getBookingEntity().getBookingId());
		
		//check room status in a synchronized way for private and shared
		bookingEntity.getBookingVsRoomEntities().parallelStream().forEach(room -> {
			//for private room check numberOfCot exists or not for that booking
			//for shared room call property server
			
			if(room.getNumOfCot() != null && Integer.parseInt(room.getNumOfCot()) > 0) { //private room
				if(bookingVsRoomService.checkIfPrivateRoomIsBooked(room.getRoomId())) {
					//room is booked
					bookingVsRoomEntities.add(room);
					
				}
			} else { //shared room call property server
				
			}
		});
		
		if(bookingVsRoomEntities.size() > 0) {
			//initiate refund and set cash part to cancelled
		} else {
			//update room
			bookingEntity.getBookingVsRoomEntities().forEach(room -> {
				//update room
				room.setModifiedBy(room.getCreatedBy());
				room.setModifiedDate(Util.getCurrentDateTime());
				room.setStatus(RoomStatus.BOOKED.ordinal());
				
				bookingVsRoomDAO.save(room);
			});
			
			//update booking vs payment
			bookingVsPaymentEntity.setStatus(PaymentStatus.COMPLETED.ordinal());
			
			bookingVsPaymentDAO.save(bookingVsPaymentEntity);
			
			//update booking master
			bookingEntity.setModifiedBy(bookingEntity.getCreatedBy());
			bookingEntity.setModifiedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.BOOKED.ordinal());
			
			bookingDAO.save(bookingEntity);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("checkRoomStatusAndBookOrRefund -- END");
		}
	}
	
}
