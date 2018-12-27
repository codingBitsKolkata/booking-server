package com.orastays.booking.bookingserver.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.booking.bookingserver.helper.SynchronizedRoomBooking;

@RestController

public class NotifyController {

	@RequestMapping(value = "/notify", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public void notifyPayment(@RequestParam Map<String, String> paramMap) {

		// updating database based on txStatus received

		if (!paramMap.get("txStatus").equals("SUCCESS")) {
			
			/*
			 * A ConfirmBookingService can be used here
			 * that will use sync method cashlessConfirmBookinf() defined in 
			 * SomeOther class rather than SynchronizedRoomBooking
			 * 
			 * This is to avoid the cash booking thread waiting
			 * since concurrency is bitch.
			 */
			
			/*
			 * SomeOther.cashlessConfirmBookinf() will
			 * check the status of associated room as done in
			 * cash payment ie validateRoomBeforeBooking() and if not booked then book.
			 * 
			 * both tasks should be done synchronously.
			 * 
			 * The below task is not to be done synchronously
			 * if related room status is booked we initiate refund 
			 * and call CashFreeApi.initiateRefund()
			 */
			
			
		} else if (paramMap.get("txStatus").equals("FLAGGED")) {
			//rebooking
		} else if (paramMap.get("txStatus").equals("FAILED")) {
			//rebooking
		} else if (paramMap.get("txStatus").equals("CANCELLED")) {
			//rebooking
		}

		/*
		 * if txStatus is not SUCCESS then dont confirm booking, revert any database
		 * changes
		 * 
		 */

	}
}
