package com.orastays.booking.bookingserver.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
import com.orastays.booking.bookingserver.entity.CancellationEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CancellationVsRoomModel extends CommonModel {

	@JsonProperty("cancellationVsRoomId")
	private Long cancellationVsRoomId;
	
	@JsonProperty("cancellationSlabId")
	private String cancellationSlabId;
	
	@JsonProperty("cancellations")
	private CancellationModel cancellationModel;
	
	@JsonProperty("bookingVsRooms")
	private BookingVsRoomModel bookingVsRoomModel;
	
}
