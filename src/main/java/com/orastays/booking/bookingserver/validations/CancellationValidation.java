package com.orastays.booking.bookingserver.validations;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.dao.CancellationDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.CancellationEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.helper.BookingStatus;
import com.orastays.booking.bookingserver.helper.CancellationHelper;
import com.orastays.booking.bookingserver.helper.MessageUtil;
import com.orastays.booking.bookingserver.model.BookingModel;

@Component
@Transactional
public class CancellationValidation {
	private static final Logger logger = LogManager.getLogger(BookingValidation.class);

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected CancellationDAO cancellationDAO;

	@Autowired
	protected BookingDAO bookingDAO;
	
	@Autowired
	protected CancellationHelper cancellationHelper;

	public void validateCancelBooking(BookingModel bookingModel) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.debug("validateCancelBooking -- START");
		}

		// First check whether the booking is already cancelled
		// To check it check whether booking status is booked and cancellation entry is
		// present in cancellation table

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		BookingEntity bookingEntity = bookingDAO.find(Long.parseLong(bookingModel.getBookingId()));
		if (bookingEntity != null) {
			if (bookingEntity.getStatus() == BookingStatus.CANCELLED.ordinal()) {
				exceptions.put(messageUtil.getBundle("bookingcancelled.error.code"),
						new Exception(messageUtil.getBundle("bookingcancelled.error.message")));

			} else if (bookingEntity.getStatus() == BookingStatus.INACTIVE.ordinal()) {
				exceptions.put(messageUtil.getBundle("bookinginactive.error.code"),
						new Exception(messageUtil.getBundle("bookinginactive.error.message")));
			} else {
				//check if cancellation entry i present. just for safety
				CancellationEntity cancellationEntity = cancellationDAO.getCancellationByBookingId(bookingModel.getBookingId());
				if(cancellationEntity != null) { //data already present
					exceptions.put(messageUtil.getBundle("bookingcancelled.error.code"),
							new Exception(messageUtil.getBundle("bookingcancelled.error.message")));
				} else {
					//process cancellation
					cancellationHelper.performCancellation(bookingEntity);
				}
			}
		} else {
			exceptions.put(messageUtil.getBundle("bookingnotfound.error.code"),
					new Exception(messageUtil.getBundle("bookingnotfound.error.message")));
		}
		if(exceptions.size() > 0) {
			throw new FormExceptions(exceptions);
		}
		if (logger.isInfoEnabled()) {
			logger.debug("validateCancelBooking -- END");
		}
	}

}
