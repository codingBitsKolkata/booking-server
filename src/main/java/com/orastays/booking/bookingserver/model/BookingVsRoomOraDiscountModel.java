package com.orastays.booking.bookingserver.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingVsRoomOraDiscountModel extends CommonModel {

	@JsonProperty("brodId")
	private Long brodId;

	@JsonProperty("rodId")
	private String rodId;

	@JsonProperty("bookingVsRooms")
	private BookingVsRoomModel bookingVsRoomModel;
	
}
