package com.orastays.booking.bookingserver.serviceImpl;

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
import com.orastays.booking.bookingserver.dao.BookingPriceDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.dao.BookingVsRoomOraDiscountDAO;
import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.entity.BookingInfoEntity;
import com.orastays.booking.bookingserver.entity.BookingPriceEntity;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
import com.orastays.booking.bookingserver.entity.BookingVsRoomOraDiscountEntity;
import com.orastays.booking.bookingserver.entity.ConvenienceEntity;
import com.orastays.booking.bookingserver.entity.GstSlabEntity;
import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.helper.BookingStatus;
import com.orastays.booking.bookingserver.helper.PropertyLocation;
import com.orastays.booking.bookingserver.helper.RoomStatus;
import com.orastays.booking.bookingserver.helper.Status;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.model.BookingPriceModel;
import com.orastays.booking.bookingserver.model.BookingVsRoomModel;
import com.orastays.booking.bookingserver.model.BookingVsRoomOraDiscountModel;
import com.orastays.booking.bookingserver.model.PaymentModel;
import com.orastays.booking.bookingserver.service.BookingService;
import com.orastays.booking.bookingserver.service.ConvenienceService;
import com.orastays.booking.bookingserver.service.GstSlabService;
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
	protected ConvenienceService convenienceService;

	@Autowired
	protected GstSlabService gstSlabService;

	@Autowired
	protected SacService sacService;

	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;
	
	@Autowired
	protected BookingPriceDAO bookingPriceDAO;
	
	@Autowired
	protected BookingVsRoomOraDiscountDAO bookingVsRoomOraDiscountDAO;
	
	@Autowired
	protected BookingInfoConverter bookingInfoConverter;
	
	@Autowired
	protected BookingInfoDAO bookingInfoDAO;
	
	
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
		populateBookingEntityForPayment(bookingModel);
		if (logger.isInfoEnabled()) {
			logger.info("addBooking -- END");
		}

		return null;

	}

	void populateBookingEntityForPayment(BookingModel bookingModel) {

		BookingEntity bookingEntity = bookingConverter.modelToEntity(bookingModel);

		// set booking master attributes
		try {
			bookingEntity.setOrabookingId("ORA" + new Date().getTime());
			bookingEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.INACTIVE.ordinal());
	
			ConvenienceEntity convenienceEntity = convenienceService.getActiveConvenienceEntity();
			bookingEntity.setConvenienceEntity(convenienceEntity);
			Double convenienceAmountWithGst = Double.parseDouble(convenienceEntity.getAmount());
			convenienceAmountWithGst = Util.calculateGstPayableAmount(convenienceAmountWithGst,
					Double.parseDouble(convenienceEntity.getGstPercentage()));
	
			bookingEntity.setConvenienceAmtWgst(Util.roundTo2Places((convenienceAmountWithGst)));
	
			Double totalPayableWithoutGst = 0.0;
			Double totalPayableWithGst = 0.0;
			
			Long bookingId = (Long) bookingDAO.save(bookingEntity);
			BookingEntity bookingEntity2 = bookingDAO.find(bookingId);
			
			for(BookingVsRoomModel bookingVsRoomModel : bookingModel.getBookingVsRoomModels()) {
				
				BookingVsRoomEntity bookingVsRoomEntity = bookingVsRoomConverter.modelToEntity(bookingVsRoomModel);
				
				bookingVsRoomEntity.setStatus(RoomStatus.INACTIVE.ordinal());
				bookingVsRoomEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
				bookingVsRoomEntity.setCreatedDate(Util.getCurrentDateTime());
				
//				bookingVsRoomEntity.setRoomId(bookingVsRoomModel.getRoomId());
//				bookingVsRoomEntity.setNumOfAdult(bookingVsRoomModel.getNumOfAdult());
//				bookingVsRoomEntity.setNumOfCot(bookingVsRoomModel.getNumOfCot());
//				bookingVsRoomEntity.setNumOfSharedBed(bookingVsRoomModel.getNumOfSharedBed());
//				bookingVsRoomEntity.setNumOfSharedCot(bookingVsRoomModel.getNumOfSharedCot());
//				bookingVsRoomEntity.setRopId(Long.parseLong(bookingVsRoomModel.getRopId()));
//				bookingVsRoomEntity.setRhdId(Long.parseLong(bookingVsRoomModel.getRhdId()));
//				bookingVsRoomEntity.setPropertyPriceDropId(Long.parseLong(bookingVsRoomModel.getPropertyPriceDropId()));
				
				Double roomActualPrice = Double.parseDouble(bookingVsRoomModel.getRoomActualPrice());
	
				GstSlabEntity gstSlabEntity = gstSlabService.getActiveGstEntity(roomActualPrice);
	
				Double gstPercentage = Double.parseDouble(gstSlabEntity.getPercentage());
	
				Double roomGstSlabPrice = Util.calculateGstPayableAmount(roomActualPrice, gstPercentage);
	
				totalPayableWithoutGst = roomActualPrice * Integer.parseInt(bookingModel.getNumOfDays());
				totalPayableWithGst = roomGstSlabPrice * Integer.parseInt(bookingModel.getNumOfDays());
	
				bookingVsRoomEntity.setRoomGSTSlabPrice(Util.roundTo2Places(roomGstSlabPrice));
	
				Double priceDiffGstVsActual = roomGstSlabPrice - roomActualPrice;
				if (bookingModel.getPropertyLoc().equals(String.valueOf(PropertyLocation.WEST_BENGAL.ordinal()))) {
					String cgstSgst = Util.roundTo2Places(priceDiffGstVsActual / 2);
					bookingVsRoomEntity.setCgst(cgstSgst);
					bookingVsRoomEntity.setSgst(cgstSgst);
				} else {
					bookingVsRoomEntity.setIgst(Util.roundTo2Places(priceDiffGstVsActual));
				}
	
				bookingVsRoomEntity.setGstSlabEntity(gstSlabEntity);
				bookingVsRoomEntity.setSacCodeEntity(sacService.getActiveSacCodeEntity());
				
				bookingVsRoomEntity.setBookingEntity(bookingEntity2);
				
				Long bookingVsRoomId = (Long) bookingVsRoomDAO.save(bookingVsRoomEntity);
				BookingVsRoomEntity bookingVsRoomEntity2 = bookingVsRoomDAO.find(bookingVsRoomId);
				
				//set booking vs room prices
				for(BookingPriceModel bookingPriceModel : bookingVsRoomModel.getBookingPriceModels()) {
					BookingPriceEntity bookingPriceEntity = new BookingPriceEntity();
					if(bookingPriceModel.getRoomVsPriceId() != null)
						bookingPriceEntity.setRoomVsPriceId(Long.parseLong(bookingPriceModel.getRoomVsPriceId()));
					bookingPriceEntity.setBookingVsRoomEntity(bookingVsRoomEntity2);
					bookingPriceEntity.setCreatedDate(Util.getCurrentDateTime());
					bookingPriceEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
					bookingPriceEntity.setStatus(Status.ACTIVE.ordinal());
					bookingPriceDAO.save(bookingPriceEntity);
				}
				
				//set booking vs ora disount
				for(BookingVsRoomOraDiscountModel bookingVsRoomOraDiscountModel : bookingVsRoomModel.getBookingVsRoomOraDiscountModels()) {
					BookingVsRoomOraDiscountEntity bookingVsRoomOraDiscountEntity = new BookingVsRoomOraDiscountEntity();
					if(bookingVsRoomOraDiscountModel.getRodId() != null)
						bookingVsRoomOraDiscountEntity.setRodId(bookingVsRoomOraDiscountModel.getRodId());
					bookingVsRoomOraDiscountEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
					bookingVsRoomOraDiscountEntity.setCreatedDate(Util.getCurrentDateTime());
					bookingVsRoomOraDiscountEntity.setStatus(Status.ACTIVE.ordinal());
					bookingVsRoomOraDiscountEntity.setBookingVsRoomEntity(bookingVsRoomEntity2);
					bookingVsRoomOraDiscountDAO.save(bookingVsRoomOraDiscountEntity);
					
				}
				
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
			
			
			//update booking entity
			bookingEntity2.setTotalPaybleWithoutGST(Util.roundTo2Places(totalPayableWithoutGst));
			bookingEntity2.setTotalPaybleWithGST(Util.roundTo2Places(totalPayableWithGst));
			bookingEntity2.setGrandTotal(Util.roundTo2Places(totalPayableWithGst + convenienceAmountWithGst));
	
			bookingDAO.update(bookingEntity2);
		} catch(Exception e) {
			e.printStackTrace();
		}
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
