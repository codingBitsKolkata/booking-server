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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private Long bookingId;

	@Column(name = "orabooking_id")
	private String orabookingId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "property_id")
	private Long propertyId;

	@Column(name = "checkin_date")
	private String checkinDate;

	@Column(name = "checkout_date")
	private String checkoutDate;

	@Column(name = "num_of_days")
	private String numOfDays;

	@Column(name = "total_payble_without_gst")
	private String totalPaybleWithoutGST;

	@Column(name = "total_payble_with_gst")
	private String totalPaybleWithGST;

	@Column(name = "grand_total")
	private String grandTotal;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "convenience_id", nullable = false)
	private ConvenienceEntity convenienceEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	private List<BookingInfoEntity> bookingInfoEntities;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	private List<BookingVsHomestayEntity> bookingVsHomestayEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	private List<BookingVsRoomEntity> bookingVsRoomEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;
	
	@Override
	public String toString() {
		return Long.toString(bookingId);
	}
}