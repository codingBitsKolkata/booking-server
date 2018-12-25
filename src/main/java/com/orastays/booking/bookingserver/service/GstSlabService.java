package com.orastays.booking.bookingserver.service;

import com.orastays.booking.bookingserver.entity.GstSlabEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;

public interface GstSlabService {

	GstSlabEntity getActiveGstEntity(Double amount) throws FormExceptions;

}