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
public class GstSlabModel extends CommonModel {

	private String gstSlabId;

	private String fromAmount;
	private String toAmount;
	private String percentage;
	private List<BookingVsRoomModel> bookingVsRoomModels;

}
