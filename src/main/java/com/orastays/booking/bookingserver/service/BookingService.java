package com.orastays.booking.bookingserver.service;

import java.util.List;

import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.model.PaymentModel;

public interface BookingService {
	BookingModel validateBooking(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getBookings(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getPropertyBookings(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getUserBookings(BookingModel bookingModel) throws FormExceptions;
	PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions;
}
