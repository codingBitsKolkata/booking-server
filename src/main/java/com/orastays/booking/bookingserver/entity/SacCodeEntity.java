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
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_sac_code")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SacCodeEntity extends CommonEntity {

	private static final long serialVersionUID = 264137474246504526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sac_code_id")
	private Long sacCodeId;

	@Column(name = "sac_name")
	private String sacCodeName;

	@Column(name = "sac_code_number")
	private String sacCodeNumber;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sacCodeEntity", cascade = { CascadeType.ALL })
	private List<BookingVsRoomEntity> bookingVsRoomEntities;

	@Override
	public String toString() {
		return Long.toString(sacCodeId);
	}

}
