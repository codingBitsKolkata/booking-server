package com.orastays.booking.bookingserver.service;

import java.util.List;

import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.BookingModel;

public interface CancellationService {
	void cancelBooking(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getUserCancellations(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getPropertyCancellations(BookingModel bookingModel) throws FormExceptions;
}
