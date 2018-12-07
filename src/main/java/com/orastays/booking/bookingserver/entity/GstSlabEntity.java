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
@Table(name = "master_gst_slab")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class GstSlabEntity extends CommonEntity {

	private static final long serialVersionUID = -3976213837715041852L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gst_slab_id")
	private Long gstSlabId;

	@Column(name = "from_amount")
	private String fromAmount;

	@Column(name = "to_amount")
	private String toAmount;

	@Column(name = "percentage")
	private String percentage;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gstSlabEntity", cascade = { CascadeType.ALL })
	private List<BookingVsRoomEntity> bookingVsRoomEntities;

	@Override
	public String toString() {
		return Long.toString(gstSlabId);
	}

}
