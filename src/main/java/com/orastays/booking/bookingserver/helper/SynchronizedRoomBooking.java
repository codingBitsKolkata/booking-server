package com.orastays.booking.bookingserver.helper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.dao.BookingVsPaymentDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;
import com.orastays.booking.bookingserver.entity.GatewayEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.service.GatewayService;
import com.orastays.booking.bookingserver.validations.BookingValidation;

@Component
public class SynchronizedRoomBooking {
	
	//private static final Logger logger = LogManager.getLogger(SynchronizedRoomBooking.class);
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected BookingValidation bookingValidation;
	
	@Autowired
	protected BookingDAO bookingDAO;
	
	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;
	
	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;
	
	@Autowired
	protected GatewayService gatewayService;
	
	public synchronized void bookRoomForCashPayments(BookingModel bm, BookingEntity be) throws FormExceptions{
		/*if (logger.isDebugEnabled()) {
			logger.debug("bookRoomForCashPayments -- Start");
		}*/
		
		bookingValidation.validateBookingBeforePayment(bm);
		
		BookingEntity bookingEntity = bookingDAO.find(be.getBookingId());
		
		bookingEntity.getBookingVsRoomEntities().parallelStream().forEach(room -> {
			room.setStatus(RoomStatus.BOOKED.ordinal());
			try {
				bookingVsRoomDAO.update(room);
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
		
		//set booking vs payment
	
		
		//if mode is partial 
		if(bm.getFormOfPayment().getMode().equals(AuthConstant.MODE_PARTIAL)) {
			
			
			Double percentage = Double.parseDouble(bm.getFormOfPayment().getPercentage());
			//calculate cashless amount
			
			Double grandTotal = Double.parseDouble(be.getGrandTotal());
			Double cashlessAmount = percentage * 0.01 * grandTotal;
			Double cashAmount = grandTotal - cashlessAmount;
			//set two rows in table for cash and cashfree
			//set cash 
			setBookingVsPaymentCash(Util.roundTo2Places(percentage), be, bm, Util.roundTo2Places(cashAmount));
			
			//set cashless
			BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
			bookingVsPaymentEntity.setBookingEntity(be);
			bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
			bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingVsPaymentEntity.setOrderId("ORA_TRNS" +  new Date().getTime());
			bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(percentage));
			GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(AuthConstant.MODE_CASHLESS);
			bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
			bookingVsPaymentEntity.setOrderAmount(Util.roundTo2Places(cashlessAmount));
			bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
			bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());
			
			//generate payment link
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("appId", "2740561eed8d839716fe23ab0472");//move to properties file
			map.add("secretKey", "99baedae4b96b79b9b0e05a87cb2680f90048bb0");
			map.add("orderId", bookingVsPaymentEntity.getOrderId());
			map.add("orderAmount", bookingVsPaymentEntity.getOrderAmount());
			map.add("orderCurrency", "INR");
			map.add("customerEmail", bm.getUserInfo().getCustomerEmail());
			map.add("customerName", bm.getUserInfo().getCustomerName());
			map.add("customerPhone", bm.getUserInfo().getCustomerPhone());
			map.add("returnUrl", "");
			map.add("notifyUrl", "");

			String url = "https://test.gocashfree.com/api/v1/order/create";
			
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
			try {
				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
				System.err.println(response);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				bookingVsPaymentDAO.save(bookingVsPaymentEntity);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		} else if(bm.getFormOfPayment().getMode().equals(AuthConstant.MODE_CASH)) {
			setBookingVsPaymentCash("100", be, bm, be.getGrandTotal());
		}
		
		
		/*if (logger.isDebugEnabled()) {
			logger.debug("bookRoomForCashPayments -- End");
		}*/
	}
	
	void setBookingVsPaymentCash(String percentage, BookingEntity be, BookingModel bm, String orderAmount) {
		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" +  new Date().getTime());
		bookingVsPaymentEntity.setPercentage(percentage);
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(AuthConstant.MODE_CASH);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		bookingVsPaymentEntity.setOrderAmount(orderAmount);
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		try {
			bookingVsPaymentDAO.save(bookingVsPaymentEntity);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
