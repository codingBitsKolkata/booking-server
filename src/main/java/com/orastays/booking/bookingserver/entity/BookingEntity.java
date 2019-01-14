/**
 * @author SUDEEP
 */
package com.orastays.booking.bookingserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_booking")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingEntity extends CommonEntity {

	private static final long serialVersionUID = -1532920959025074277L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	@JsonProperty("bookingId")
	private Long bookingId;

	@Column(name = "orabooking_id")
	@JsonProperty("orabookingId")
	private String orabookingId;

	@Column(name = "user_id")
	@JsonProperty("userId")
	private Long userId;

	@Column(name = "property_id")
	@JsonProperty("propertyId")
	private Long propertyId;

	@Column(name = "checkin_date")
	@JsonProperty("checkinDate")
	private String checkinDate;

	@Column(name = "checkout_date")
	@JsonProperty("checkoutDate")
	private String checkoutDate;

	@Column(name = "num_of_days")
	@JsonProperty("numOfDays")
	private String numOfDays;
	
	@Column(name = "booking_approval")
	@JsonProperty("bookingApproval")
	private String bookingApproval;
	
	@Column(name = "convenience_amt_wgst")
	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	

	@Column(name = "total_payble_without_gst")
	@JsonProperty("totalPaybleWithoutGST")
	private String totalPaybleWithoutGST;

	@Column(name = "total_payble_with_gst")
	@JsonProperty("totalPaybleWithGST")
	private String totalPaybleWithGST;

	@Column(name = "grand_total")
	@JsonProperty("grandTotal")
	private String grandTotal;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingInfos")
	private BookingInfoEntity bookingInfoEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsRooms")
	private List<BookingVsRoomEntity> bookingVsRoomEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellations")
	private CancellationEntity cancellationEntity;
	
	@Column(name = "property_offer")
	@JsonProperty("propertyOffer")
	private String propertyOffer;

	@Column(name = "convenience_fee")
	@JsonProperty("convenienceFee")
	private String convenienceFee;

	@Column(name = "convenience_gst_percentage")
	@JsonProperty("convenienceGSTPercentage")
	private String convenienceGSTPercentage;

	@Column(name = "convenience_gst_amount")
	@JsonProperty("convenienceGSTAmount")
	private String convenienceGSTAmount;
	
	@Column(name = "total_amount")
	@JsonProperty("totalAmount")
	private String totalAmount;

	@Column(name = "amount_payable")
	@JsonProperty("amountPayable")
	private String amountPayable;
	
	@Override
	public String toString() {
		return Long.toString(bookingId);
	}
}