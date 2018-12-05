package com.orastays.booking.bookingserver.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class GatewayModel extends CommonModel {

	private String gatewayId;

	private String gatewayName;

	private List<BookingVsPaymentModel> bookingVsPaymentModels;

}
