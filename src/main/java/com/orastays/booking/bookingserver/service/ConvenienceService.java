package com.orastays.booking.bookingserver.service;

import com.orastays.booking.bookingserver.entity.ConvenienceEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.ConvenienceModel;

public interface ConvenienceService {

	ConvenienceEntity getActiveConvenienceEntity();
	ConvenienceModel getActiveConvenienceModel() throws FormExceptions;
}