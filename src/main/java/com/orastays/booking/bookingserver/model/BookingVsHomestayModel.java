package com.orastays.booking.bookingserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingVsHomestayModel extends CommonModel {

	private String bookingHomestayId;
	private String bookingApproval;
	private BookingModel bookingModels;

}
