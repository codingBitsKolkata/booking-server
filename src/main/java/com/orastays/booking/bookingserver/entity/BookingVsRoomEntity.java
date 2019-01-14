/**
 * @author SUDEEP
 */
package com.orastays.booking.bookingserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_vs_room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsRoomEntity extends CommonEntity {

	private static final long serialVersionUID = -8084405527617612386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_room_id")
	@JsonProperty("bookingVsRoomId")
	private Long bookingVsRoomId;

	@Column(name = "ora_room_name")
	@JsonProperty("oraRoomName")
	private String oraRoomName;

	@Column(name = "room_id")
	@JsonProperty("roomId")
	private String roomId;

	@Column(name = "no_of_guest")
	@JsonProperty("noOfGuest")
	private String noOfGuest;

	@Column(name = "no_of_child")
	@JsonProperty("noOfChild")
	private String noOfChild;

	@Column(name = "num_of_cot")
	@JsonProperty("numOfCot")
	private String numOfCot;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("bookings")
	private BookingEntity bookingEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "sac_code_id", nullable = false)
	@JsonProperty("sacCodes")
	private SacCodeEntity sacCodeEntity;

	@Column(name = "num_of_shared_bed")
	@JsonProperty("numOfSharedBed")
	private String numOfSharedBed;

	@Column(name = "num_of_shared_cot")
	@JsonProperty("numOfSharedCot")
	private String numOfSharedCot;

	@Column(name = "room_vs_offer_id")
	@JsonProperty("roomVsOfferId")
	private String roomVsOfferId;

	@Column(name = "extra_person_price")
	@JsonProperty("extraPersonPrice")
	private String extraPersonPrice;

	@Column(name = "ora_price")
	@JsonProperty("oraPrice")
	private String oraPrice;

	@Column(name = "ora_discount")
	@JsonProperty("oraDiscount")
	private String oraDiscount;

	@Column(name = "host_discount")
	@JsonProperty("hostDiscount")
	private String hostDiscount;

	@Column(name = "room_offer")
	@JsonProperty("roomOffer")
	private String roomOffer;

	@Column(name = "price_drop")
	@JsonProperty("priceDrop")
	private String priceDrop;

	@Column(name = "gst_percentage")
	@JsonProperty("gstPercentage")
	private String gstPercentage;

	@Column(name = "amount_with_gst")
	@JsonProperty("amountWithGST")
	private String amountWithGST;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingVsRoomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellationVsRooms")
	private CancellationVsRoomEntity cancellationVsRoomEntity;

	@Override
	public String toString() {
		return Long.toString(bookingVsRoomId);
	}
}
