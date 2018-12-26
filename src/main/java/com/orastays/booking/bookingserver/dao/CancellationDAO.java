package com.orastays.booking.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.CancellationEntity;

@Repository
public class CancellationDAO extends GenericDAO<CancellationEntity, Long> {


	private static final long serialVersionUID = 5876332836788821115L;

	public CancellationDAO() {
		super(CancellationEntity.class);

	}
}