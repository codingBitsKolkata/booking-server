package com.orastays.booking.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.BookingEntity;

@Repository
public class BookingDAO extends GenericDAO<BookingEntity, Long> {

	private static final long serialVersionUID = -5070151407565218463L;

	public BookingDAO() {
		super(BookingEntity.class);

	}
}
