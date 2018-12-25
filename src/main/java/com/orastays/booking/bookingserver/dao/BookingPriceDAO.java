package com.orastays.booking.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.BookingPriceEntity;

@Repository
public class BookingPriceDAO extends GenericDAO<BookingPriceEntity, Long> {

	private static final long serialVersionUID = 6454932478890664232L;

	public BookingPriceDAO() {
		super(BookingPriceEntity.class);

	}
}