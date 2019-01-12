package com.orastays.booking.bookingserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingModel extends CommonModel {

	@JsonProperty("bookingId")
	private String bookingId;

	@JsonProperty("orabookingId")
	private String orabookingId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("propertyId")
	private String propertyId;

	@JsonProperty("propertyLoc")
	private String propertyLoc;

	@JsonProperty("checkinDate")
	private String checkinDate;

	@JsonProperty("checkoutDate")
	private String checkoutDate;

	@JsonProperty("numOfDays")
	private String numOfDays;

	@JsonProperty("totalPaybleWithoutGST")
	private String totalPaybleWithoutGST;

	@JsonProperty("totalPaybleWithGST")
	private String totalPaybleWithGST;
	
	@JsonProperty("bookingInfos")
	private BookingInfoModel bookingInfoModel;

	@JsonProperty("bookingApproval")
	private String bookingApproval;
	
	@JsonProperty("bookingVsRooms")
	private List<BookingVsRoomModel> bookingVsRoomModels;
	
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;
	
	@JsonProperty("formOfPayment")
	private FormOfPayment formOfPayment;
	
	@JsonProperty("cancellations")
	private CancellationModel cancellationModel;
	
	@JsonProperty("propertyOffer")
	private String propertyOffer;

	@JsonProperty("convenienceFee")
	private String convenienceFee;

	@JsonProperty("convenienceGSTPercentage")
	private String convenienceGSTPercentage;

	@JsonProperty("convenienceGSTAmount")
	private String convenienceGSTAmount;
	
	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("amountPayable")
	private String amountPayable;
}



