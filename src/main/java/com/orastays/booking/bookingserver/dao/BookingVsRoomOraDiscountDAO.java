package com.orastays.booking.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.BookingVsRoomOraDiscountEntity;

@Repository
public class BookingVsRoomOraDiscountDAO extends GenericDAO<BookingVsRoomOraDiscountEntity, Long> {

	private static final long serialVersionUID = 1467817543436555066L;

	public BookingVsRoomOraDiscountDAO() {
		super(BookingVsRoomOraDiscountEntity.class);

	}
}
