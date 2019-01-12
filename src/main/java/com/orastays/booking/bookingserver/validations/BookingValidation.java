package com.orastays.booking.bookingserver.validations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.helper.AccommodationStatus;
import com.orastays.booking.bookingserver.helper.MessageUtil;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.model.BookingVsRoomModel;

@Component
@Transactional
public class BookingValidation {

	private static final Logger logger = LogManager.getLogger(BookingValidation.class);

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected BookingDAO bookingDAO;

	public void validateBookingBeforePayment(BookingModel bookingModel) throws FormExceptions {
		if (logger.isDebugEnabled()) {
			logger.debug("validateBookingBeforePayment -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		CopyOnWriteArrayList<BookingModel> booked = new CopyOnWriteArrayList<>();

		List<BookingVsRoomModel> bookingVsRoomModels = bookingModel.getBookingVsRoomModels();

		bookingVsRoomModels.parallelStream().forEach(room -> {
			if (room.getAccommodationModel().getAccommodationId().equals(String.valueOf(AccommodationStatus.PRIVATE.ordinal()))) {
				boolean privateRoomBookedStatus = bookingDAO.getBookedPivateRoom(bookingModel.getPropertyId(),
						room.getOraRoomName(), bookingModel.getCheckinDate(), bookingModel.getCheckoutDate());
				if (privateRoomBookedStatus) {
					booked.add(bookingModel);
				}
			}

			else if (room.getAccommodationModel().getAccommodationId()
					.equals(String.valueOf(AccommodationStatus.SHARED.ordinal()))) {
				Long numberOfSharedBed = Long.parseLong(room.getNumOfSharedBed());
				Long numberOfSharedCot = Long.parseLong(room.getNumOfSharedCot());
				Long totalNumberOfSharedBed = Long.parseLong(room.getTotalNumOfSharedBed());
				Long totalNumberOfSharedCot = Long.parseLong(room.getTotalNumOfSharedCot());
				boolean sharedRoomBookedStatus = bookingDAO.getBookedSharedRoom(bookingModel.getPropertyId(),
						room.getOraRoomName(), bookingModel.getCheckinDate(), bookingModel.getCheckoutDate(),
						numberOfSharedBed, totalNumberOfSharedBed, numberOfSharedCot, totalNumberOfSharedCot);
				if (sharedRoomBookedStatus) {
					booked.add(bookingModel);
				}
			}
		});

		if (booked.size() > 0) {
			exceptions.put(messageUtil.getBundle("roombooked.error.code"),
					new Exception(messageUtil.getBundle("roombooked.error.message")));
			throw new FormExceptions(exceptions);
		}

		// Set BookingModel

		if (logger.isDebugEnabled()) {
			logger.debug("validateBookingBeforePayment -- End");
		}
	}
}
