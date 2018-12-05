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
public class SacCodeModel extends CommonModel {

	private String sacCodeId;

	private String sacName;
	private String sacCodeNumber;
	private List<BookingVsRoomModel> bookingVsRoomModels;

}
