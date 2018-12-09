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
public class BookingPriceModel extends CommonModel {

	@JsonProperty("bookingPriceId")
	private String bookingPriceId;

	@JsonProperty("roomVsPriceId")
	private String roomVsPriceId;

	@JsonProperty("bookingVsRooms")
	private BookingVsRoomModel bookingVsRoomModel;

}
