package com.orastays.booking.bookingserver.service;

import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.BookingModel;

public interface BookingService {
	BookingModel validateBooking(BookingModel bookingModel) throws FormExceptions;
}
