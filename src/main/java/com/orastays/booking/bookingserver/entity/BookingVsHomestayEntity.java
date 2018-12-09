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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_vs_homestay")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsHomestayEntity extends CommonEntity {

	private static final long serialVersionUID = -8383061569379000565L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_homestay_id")
	@JsonProperty("bookingHomestayId")
	private Long bookingHomestayId;

	@Column(name = "booking_approval")
	@JsonProperty("bookingApproval")
	private Long bookingApproval;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("bookings")
	private BookingEntity bookingEntity;

	@Override
	public String toString() {
		return Long.toString(bookingHomestayId);
	}
}