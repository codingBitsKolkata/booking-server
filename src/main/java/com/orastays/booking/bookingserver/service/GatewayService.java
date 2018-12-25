package com.orastays.booking.bookingserver.service;

import com.orastays.booking.bookingserver.entity.GatewayEntity;

public interface GatewayService {
	GatewayEntity getGatewayEntity(String gatewayName);
}
