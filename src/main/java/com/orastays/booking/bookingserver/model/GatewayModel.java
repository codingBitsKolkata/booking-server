package com.orastays.booking.bookingserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class GatewayModel extends CommonModel {

	@JsonProperty("gatewayId")
	private Long gatewayId;

	@JsonProperty("gatewayName")
	private String gatewayName;

	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;

}
