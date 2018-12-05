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
public class BookingModel extends CommonModel {

	private String bookingVsPaymentId;

	private String amountPaid;
	private String orderId;
	private String orderAmount;
	private String referenceId;
	private String txStatus;
	private String paymentMode;
	private String txMsg;
	private String txTime;
	private String signature;
	private String otherInfo;

	private ConvenienceModel convenienceModels;

	private List<BookingInfoModel> bookingInfoModels;
	private List<BookingVsHomestayModel> bookingVsHomestayModels;
	private List<BookingVsRoomModel> bookingVsRoomModels;
	private List<BookingVsPaymentModel> bookingVsPaymentModels;

}
