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
@Table(name = "booking_vs_payment")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsPaymentEntity extends CommonEntity {

	private static final long serialVersionUID = -3411930437094752976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_payment_id")
	private Long bookingVsPaymentId;

	@Column(name = "amount_paid")
	private String amountPaid;

	@Column(name = "orderId")
	private String orderId;

	@Column(name = "orderAmount")
	private String orderAmount;

	@Column(name = "referenceId")
	private String referenceId;

	@Column(name = "txStatus")
	private String txStatus;

	@Column(name = "paymentMode")
	private String paymentMode;

	@Column(name = "txMsg")
	private String txMsg;

	@Column(name = "txTime")
	private String txTime;

	@Column(name = "signature")
	private String signature;

	@Column(name = "other_info")
	private String otherInfo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	private BookingEntity bookingEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "gateway_id", nullable = false)
	private GatewayEntity gatewayEntity;

	@Override
	public String toString() {
		return Long.toString(bookingVsPaymentId);
	}
}