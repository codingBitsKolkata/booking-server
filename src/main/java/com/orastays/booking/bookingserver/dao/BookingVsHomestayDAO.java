package com.orastays.booking.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.BookingVsHomestayEntity;

@Repository
public class BookingVsHomestayDAO extends GenericDAO<BookingVsHomestayEntity, Long> {

	private static final long serialVersionUID = 8998083269946087045L;

	public BookingVsHomestayDAO() {
		super(BookingVsHomestayEntity.class);

	}
}
