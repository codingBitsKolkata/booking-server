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
public class ConvenienceModel extends CommonModel {

	@JsonProperty("convenienceId")
	private Long convenienceId;

	@JsonProperty("amount")
	private String amount;

	@JsonProperty("bookings")
	private List<BookingModel> bookingModels;
}
