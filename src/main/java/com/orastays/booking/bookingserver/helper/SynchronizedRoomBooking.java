package com.orastays.booking.bookingserver.helper;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.dao.BookingVsPaymentDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;
import com.orastays.booking.bookingserver.entity.GatewayEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.model.PaymentModel;
import com.orastays.booking.bookingserver.service.GatewayService;
import com.orastays.booking.bookingserver.validations.BookingValidation;

@Component
public class SynchronizedRoomBooking {

	private static final Logger logger = LogManager.getLogger(SynchronizedRoomBooking.class);

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

	@Autowired
	protected CashFreeApi cashFreeApi;

	@Autowired
	protected MessageUtil messageUtil;

	public synchronized PaymentModel bookRoomForCashPayments(BookingModel bm, BookingEntity be) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("bookRoomForCashPayments -- Start");
		}

		bookingValidation.validateBookingBeforePayment(bm);


		be.getBookingVsRoomEntities().parallelStream().forEach(room -> {
			room.setStatus(RoomStatus.BOOKED.ordinal());
			try {
				bookingVsRoomDAO.update(room);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		});

		// set booking vs payment

		// if mode is partial
		if (bm.getFormOfPayment().getMode().equals(AuthConstant.MODE_PARTIAL)) {

			Double percentage = Double.parseDouble(bm.getFormOfPayment().getPercentage());
			// calculate cashless amount

			Double grandTotal = Double.parseDouble(be.getGrandTotal());
			Double cashlessAmount = percentage * 0.01 * grandTotal;
			Double cashAmount = grandTotal - cashlessAmount;
			// set two rows in table for cash and cashfree

			// set cashless
			BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
			bookingVsPaymentEntity.setBookingEntity(be);
			bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
			bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
			bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(percentage));
			GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(AuthConstant.MODE_CASHLESS);
			bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
			bookingVsPaymentEntity.setOrderAmount(Util.roundTo2Places(cashlessAmount));
			bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
			bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());

			PaymentModel paymentModel = cashFreeApi.getPaymentLink(bm, be, bookingVsPaymentEntity);

			// set cash
			setBookingVsPaymentCash(Util.roundTo2Places(percentage), be, bm, Util.roundTo2Places(cashAmount));

			return paymentModel;

		} else if (bm.getFormOfPayment().getMode().equals(AuthConstant.MODE_CASH)) {
			setBookingVsPaymentCash("100", be, bm, be.getGrandTotal());
		}

		if (logger.isInfoEnabled()) {
			logger.info("bookRoomForCashPayments -- End");
		}
		return null;
	}

	void setBookingVsPaymentCash(String percentage, BookingEntity be, BookingModel bm, String orderAmount) {
		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
		bookingVsPaymentEntity.setPercentage(percentage);
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(AuthConstant.MODE_CASH);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		bookingVsPaymentEntity.setOrderAmount(orderAmount);
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		try {
			bookingVsPaymentDAO.save(bookingVsPaymentEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PaymentModel bookRoomForCashLessPayments(BookingModel bm, BookingEntity be) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("bookRoomForCashLessPayments -- Start");
		}
		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
		bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(100.00)); // remove hardcode
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(AuthConstant.MODE_CASHLESS);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		bookingVsPaymentEntity.setOrderAmount(be.getGrandTotal());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());

		PaymentModel paymentModel = cashFreeApi.getPaymentLink(bm, be, bookingVsPaymentEntity);
		if (logger.isInfoEnabled()) {
			logger.info("bookRoomForCashLessPayments -- End");
		}
		return paymentModel;
	}
}
