package com.orastays.booking.bookingserver.service;

import com.orastays.booking.bookingserver.entity.GstSlabEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.GstSlabModel;

public interface GstSlabService {

	GstSlabEntity getActiveGstEntity(Double amount) throws FormExceptions;
	GstSlabModel getActiveGstModel(Double amount) throws FormExceptions;
}