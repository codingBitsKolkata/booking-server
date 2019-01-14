package com.orastays.booking.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.ConvenienceEntity;

@Repository
public class ConvenienceDAO extends GenericDAO<ConvenienceEntity, Long> {

	private static final long serialVersionUID = 764287020568903042L;

	public ConvenienceDAO() {
		super(ConvenienceEntity.class);

	}
}
