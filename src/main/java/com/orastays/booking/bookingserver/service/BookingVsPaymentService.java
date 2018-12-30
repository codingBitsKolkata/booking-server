package com.orastays.booking.bookingserver.service;

import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;

public interface BookingVsPaymentService {
	BookingVsPaymentEntity getBookingVsPaymentEntityByOrderId(String orderId);
}
