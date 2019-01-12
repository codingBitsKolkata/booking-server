package com.orastays.booking.bookingserver.model;

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
public class BookingVsRoomModel extends CommonModel {

	@JsonProperty("bookingVsRoomId")
	private String bookingVsRoomId;

	@JsonProperty("oraRoomName")
	private String oraRoomName;
	
	@JsonProperty("roomId")
	private String roomId;

	@JsonProperty("noOfGuest")
	private String noOfGuest;
	
	@JsonProperty("noOfChild")
	private String noOfChild;
	
	@JsonProperty("numOfCot")
	private String numOfCot;
	
	@JsonProperty("bookings")
	private BookingModel bookingModel;

	@JsonProperty("sacCodes")
	private SacCodeModel sacCodeModel;
	
	@JsonProperty("accommodation")
	private AccommodationModel accommodationModel;
	
	@JsonProperty("numOfSharedBed")
	private String numOfSharedBed;
	
	@JsonProperty("numOfSharedCot")
	private String numOfSharedCot;
	
	@JsonProperty("totalNumOfSharedCot")
	private String totalNumOfSharedCot;
	
	@JsonProperty("totalNumOfSharedBed")
	private String totalNumOfSharedBed;
	
	@JsonProperty("cancellationVsRooms")
	private CancellationVsRoomModel cancellationVsRoomModel;
	
	@JsonProperty("roomVsOfferId")
	private String roomVsOfferId;
	
	@JsonProperty("extraPersonPrice")
	private String extraPersonPrice;
	
	@JsonProperty("oraPrice")
	private String oraPrice;
	
	@JsonProperty("oraDiscount")
	private String oraDiscount;
	
	@JsonProperty("hostDiscount")
	private String hostDiscount;
	
	@JsonProperty("roomOffer")
	private String roomOffer;
	
	@JsonProperty("priceDrop")
	private String priceDrop;
	
	@JsonProperty("gstPercentage")
	private String gstPercentage;
	
	@JsonProperty("amountWithGST")
	private String amountWithGST;
	
}
