package com.orastays.booking.bookingserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingVsHomestayModel extends CommonModel {

	@JsonProperty("bookingHomestayId")
	private String bookingHomestayId;

	@JsonProperty("bookingApproval")
	private String bookingApproval;

	@JsonProperty("bookings")
	private BookingModel bookingModel;

}
