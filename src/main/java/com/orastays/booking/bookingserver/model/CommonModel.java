/**
 * @author SUDEEP
 */
package com.orastays.booking.bookingserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CommonModel {

	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("createdDate")
	private String createdDate;
	
	@JsonProperty("createdBy")
	private Long createdBy;
	
	@JsonProperty("modifiedDate")
	private String modifiedDate;
	
	@JsonProperty("modifiedBy")
	private Long modifiedBy;
	
	@JsonProperty("userToken")
	private String userToken;
	
	@JsonProperty("languageId")
	private String languageId;

}