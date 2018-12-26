package com.orastays.booking.bookingserver.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.CancellationEntity;
import com.orastays.booking.bookingserver.entity.CancellationVsRoomEntity;
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
public class CancellationModel extends CommonModel {

	@JsonProperty("cancellationId")
	private Long cancellationId;

	@JsonProperty("totalPaybleWithoutGst")
	private String totalPaybleWithoutGst;

	@JsonProperty("totalAmountPaid")
	private String totalAmountPaid;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("reasonForCancellation")
	private String reasonForCancellation;

	@JsonProperty("totalAmountRefunded")
	private String totalAmountRefunded;

	@JsonProperty("bookings")
	private BookingModel bookingModel;
	
	@JsonProperty("cancellationVsRooms")
	private List<CancellationVsRoomModel> cancellationVsRoomsModels;

}



