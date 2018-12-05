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
import javax.persistence.Table;

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
	private Long bookingVsRoomId;

	@Column(name = "room_id")
	private String roomId;

	@Column(name = "num_of_adult")
	private String numOfAdult;

	@Column(name = "num_of_cot")
	private String numOfCot;

	@Column(name = "room_vs_price_bp_id")
	private Long roomVsPriceBPId;

	@Column(name = "rhd_id")
	private Long rhdId;

	@Column(name = "rop_id")
	private Long ropId;

	@Column(name = "dco_id")
	private Long dcoId;

	@Column(name = "rod_id")
	private Long rodId;

	@Column(name = "room_vs_price_cot_id")
	private Long roomVsPriceCotId;

	@Column(name = "price_drop_id")
	private Long priceDropId;

	@Column(name = "room_gst_slab_price")
	private String roomGSTSlabPrice;

	@Column(name = "sgst")
	private String sgst;

	@Column(name = "cgst")
	private String cgst;

	@Column(name = "igst")
	private String igst;

	@Column(name = "room_actual_price")
	private String roomActualPrice;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	private BookingEntity bookingEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "sac_code_id", nullable = false)
	private SacCodeEntity sacCodeEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "gst_slab_id", nullable = false)
	private GstSlabEntity gstSlabEntity;

	@Override
	public String toString() {
		return Long.toString(bookingVsRoomId);
	}
}
