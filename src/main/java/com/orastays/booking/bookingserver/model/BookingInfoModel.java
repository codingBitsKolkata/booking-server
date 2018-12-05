package com.orastays.booking.bookingserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingInfoModel extends CommonModel {

	
	private String bookingInfoId;

	private String name;
	private String address;
	private String companyName;
	private String gstin;
	private String mobile;
	private String email;
	private String checkinPrefTime;
	private String checkoutPrefTime;
	private String identityId;
	private String idFileUrl;
	private BookingModel bookingModels;

}
