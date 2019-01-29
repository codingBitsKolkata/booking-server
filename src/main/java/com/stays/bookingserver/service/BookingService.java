package com.stays.bookingserver.service;

import java.util.List;

import com.stays.bookingserver.exceptions.FormExceptions;
import com.stays.bookingserver.model.BookingModel;
import com.stays.bookingserver.model.PaymentModel;

public interface BookingService {
	PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions;
	BookingModel validateBooking(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getPropertyBookings(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getUserBookings(BookingModel bookingModel) throws FormExceptions;
	List<BookingModel> getBookings(BookingModel bookingModel) throws FormExceptions;
}
