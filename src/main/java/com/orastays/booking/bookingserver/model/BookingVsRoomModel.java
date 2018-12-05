package com.orastays.booking.bookingserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingVsRoomModel extends CommonModel {

	private String bookingVsRoomId;

	private String roomId;
	private String numOfAdult;
	private String numOfCot;
	private String roomVsPriceBpId;
	private String rhdId;
	private String ropId;
	private String dcoId;
	private String rodId;
	private String roomVsPriceCotId;
	private String priceDropId;
	private String roomGstSlabPrice;
	private String sgst;
	private String cgst;
	private String igst;
	private String roomActualPrice;

	private BookingModel bookingModels;
	private SacCodeModel sacCodeModels;
	private GstSlabModel gstSlabModels;

}
