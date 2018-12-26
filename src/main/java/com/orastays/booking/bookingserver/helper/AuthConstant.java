package com.orastays.booking.bookingserver.helper;

public final class AuthConstant {

	private AuthConstant() {}

	public static final String COMMON_SUCCESS_CODE = "common.success.code";
	public static final String COMMON_SUCCESS_MESSAGE = "common.success.message";
	public static final String OUTGOING = "RESPONSE";
	public static final String INCOMING = "REQUEST";
	public static final String COMMON_ERROR_CODE = "common.error.code"; 
	public static final String COMMON_ERROR_MESSAGE = "common.error.message";
	public static final String FOR_ROW = "for ROW";
	
	
	//Payment Mode
	public static final String MODE_CASH = "cash";
	public static final String MODE_CASHLESS = "cashfree";
	public static final String MODE_PARTIAL = "partial";
	
	//cashfreeapi status
	public static final String CASHFREE_OK = "OK";
	public static final String CASHFREE_ERROR = "ERROR";
}
