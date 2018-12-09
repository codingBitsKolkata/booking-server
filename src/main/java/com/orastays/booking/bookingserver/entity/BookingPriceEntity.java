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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_price")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingPriceEntity extends CommonEntity {

	private static final long serialVersionUID = 1394133384784002535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_price_id")
	@JsonProperty("bookingPriceId")
	private Long bookingPriceId;
	
	@Column(name = "room_vs_price_id")
	@JsonProperty("roomVsPriceId")
	private Long roomVsPriceId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_vs_room_id", nullable = false)
	@JsonProperty("bookingVsRooms")
	private BookingVsRoomEntity bookingVsRoomEntity;

	@Override
	public String toString() {
		return Long.toString(bookingPriceId);
	}
}
