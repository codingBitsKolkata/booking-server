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
public class ConvenienceModel extends CommonModel {

	private String bookingId;

	private String orabookingId;
	private String userId;
	private String propertyId;
	private String checkinDate;
	private String checkoutDate;
	private String numOfDays;
	private String totalPaybleWithoutGst;
	private String totalPaybleWithGst;
	private String grandTotal;
	private List<BookingModel> bookingModels;

}
