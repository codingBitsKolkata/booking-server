package com.orastays.booking.bookingserver.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orastays.booking.bookingserver.converter.BookingConverter;
import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.helper.Status;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingConverter bookingConverter;

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;

	@Override
	public BookingModel validateBooking(BookingModel bookingModel) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- START");
		}

		BookingModel bookingModel2 = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put("bookingId", bookingModel.getBookingId());
			innerMap1.put("propertyId", bookingModel.getPropertyId());
			innerMap1.put("userId", bookingModel.getUserId());
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".BookingEntity", outerMap1);

			bookingModel2 = bookingConverter.entityToModel(bookingDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in validateBooking -- " + Util.errorToString(e));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- END");
		}

		return bookingModel2;
	}

	@Override
	public List<BookingModel> getBookings(BookingModel bookingModel) throws FormExceptions {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("getBookings -- START");
		}

		List<BookingModel> bookingModels = null;
		try {
			bookingModels = bookingConverter.entityListToModelList(bookingDAO.getBookingsByCheckInDate(bookingModel.getCheckinDate(), bookingModel.getPropertyId()));
			System.err.println("here");
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getBookings -- " + Util.errorToString(e));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("getBookings -- END");
		}

		return bookingModels;
	}

}
