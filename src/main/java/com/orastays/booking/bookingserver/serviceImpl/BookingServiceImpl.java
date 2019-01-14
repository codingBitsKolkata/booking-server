package com.orastays.booking.bookingserver.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
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
import com.orastays.booking.bookingserver.converter.BookingInfoConverter;
import com.orastays.booking.bookingserver.converter.BookingVsRoomConverter;
import com.orastays.booking.bookingserver.dao.BookingDAO;
import com.orastays.booking.bookingserver.dao.BookingInfoDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingInfoEntity;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.helper.AuthConstant;
import com.orastays.booking.bookingserver.helper.BookingStatus;
import com.orastays.booking.bookingserver.helper.RoomStatus;
import com.orastays.booking.bookingserver.helper.Status;
import com.orastays.booking.bookingserver.helper.SynchronizedRoomBooking;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.model.BookingVsRoomModel;
import com.orastays.booking.bookingserver.model.PaymentModel;
import com.orastays.booking.bookingserver.service.BookingService;
import com.orastays.booking.bookingserver.service.SacService;
import com.orastays.booking.bookingserver.validations.BookingValidation;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingConverter bookingConverter;

	@Autowired
	protected BookingVsRoomConverter bookingVsRoomConverter;

	@Autowired
	protected BookingValidation bookingValidation;

	@Autowired
	protected SacService sacService;

	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;
	
	@Autowired
	protected BookingInfoConverter bookingInfoConverter;
	
	@Autowired
	protected BookingInfoDAO bookingInfoDAO;
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected SynchronizedRoomBooking synchronizedRoomBooking;

	@Override
	public BookingModel validateBooking(BookingModel bookingModel) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- START");
		}

		BookingModel bookingModel2 = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(BookingStatus.BOOKED.ordinal()));
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
		if (logger.isInfoEnabled()) {
			logger.info("getBookings -- START");
		}

		List<BookingModel> bookingModels = null;
		try {
			bookingModels = bookingConverter.entityListToModelList(
					bookingDAO.getBookingsByCheckInDate(bookingModel.getCheckinDate(), bookingModel.getPropertyId()));
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

	@Override
	public PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("addBooking -- START");
		}

		bookingValidation.validateBookingBeforePayment(bookingModel);
		PaymentModel paymentModel = populateBookingEntityForPayment(bookingModel);
		if (logger.isInfoEnabled()) {
			logger.info("addBooking -- END");
		}

		return paymentModel;

	}

	PaymentModel populateBookingEntityForPayment(BookingModel bookingModel) {

		BookingEntity bookingEntity = bookingConverter.modelToEntity(bookingModel);
		PaymentModel paymentModel = null;
		// set booking master attributes
		try {
			bookingEntity.setOrabookingId("ORA" + new Date().getTime());
			bookingEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.INACTIVE.ordinal());
	
			
			Long bookingId = (Long) bookingDAO.save(bookingEntity);
			BookingEntity bookingEntity2 = bookingDAO.find(bookingId);
			List<BookingVsRoomEntity> bookingVsRoomEntities = new ArrayList<>();
			
			for(BookingVsRoomModel bookingVsRoomModel : bookingModel.getBookingVsRoomModels()) {
				
				BookingVsRoomEntity bookingVsRoomEntity = bookingVsRoomConverter.modelToEntity(bookingVsRoomModel);
				
				bookingVsRoomEntity.setStatus(RoomStatus.INACTIVE.ordinal());
				bookingVsRoomEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
				bookingVsRoomEntity.setCreatedDate(Util.getCurrentDateTime());
	
				
				bookingVsRoomEntity.setBookingEntity(bookingEntity2);
				
				bookingVsRoomDAO.save(bookingVsRoomEntity);
				bookingVsRoomEntities.add(bookingVsRoomEntity);
			}
			
			BookingInfoEntity bookingInfoEntity = bookingInfoConverter.modelToEntity(bookingModel.getBookingInfoModel());
			// set booking vs info
			if(bookingInfoEntity == null) {
				bookingInfoEntity = new BookingInfoEntity();
			}
			bookingInfoEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingInfoEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingInfoEntity.setStatus(Status.ACTIVE.ordinal());
			bookingInfoEntity.setBookingEntity(bookingEntity2);
			
			bookingInfoDAO.save(bookingInfoEntity);
			
			
	
			//set booking vs payment
			bookingEntity2.setBookingVsRoomEntities(bookingVsRoomEntities);
			if(bookingModel.getFormOfPayment().getMode().equalsIgnoreCase(AuthConstant.MODE_CASH) || bookingModel.getFormOfPayment().getMode().equalsIgnoreCase(AuthConstant.MODE_PARTIAL)) {
				paymentModel = synchronizedRoomBooking.bookRoomForCashPayments(bookingModel, bookingEntity2);
			} else {
				paymentModel = synchronizedRoomBooking.bookRoomForCashLessPayments(bookingModel, bookingEntity2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return paymentModel;
	}

	@Override
	public List<BookingModel> getPropertyBookings(BookingModel bookingModel) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("getPropertyBookings -- START");
		}

		List<BookingModel> bookingModels = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(BookingStatus.BOOKED.ordinal()));
			innerMap1.put("propertyId", bookingModel.getPropertyId());
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".BookingEntity", outerMap1);

			bookingModels = bookingConverter.entityListToModelList(bookingDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getPropertyBookings -- " + Util.errorToString(e));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("getPropertyBookings -- END");
		}

		return bookingModels;
	}

	@Override
	public List<BookingModel> getUserBookings(BookingModel bookingModel) throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("getUserBookings -- START");
		}

		List<BookingModel> bookingModels = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(BookingStatus.BOOKED.ordinal()));
			innerMap1.put("userId", bookingModel.getUserId());
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".BookingEntity", outerMap1);

			bookingModels = bookingConverter.entityListToModelList(bookingDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getUserBookings -- " + Util.errorToString(e));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("getUserBookings -- END");
		}

		return bookingModels;
	}
	
}
