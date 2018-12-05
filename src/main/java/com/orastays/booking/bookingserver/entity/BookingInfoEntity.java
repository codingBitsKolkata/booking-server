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
@Table(name = "booking_info")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingInfoEntity extends CommonEntity {

	private static final long serialVersionUID = 1394133384784002535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_info_id")
	private Long bookingInfoId;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Column(name = "checkin_pref_time")
	private String checkinPrefTime;

	@Column(name = "checkout_pref_time")
	private String checkoutPrefTime;

	@Column(name = "identity_id")
	private String identityId;

	@Column(name = "id_file_url")
	private String idFileUrl;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	private BookingEntity bookingEntity;

	@Override
	public String toString() {
		return Long.toString(bookingInfoId);
	}
}
