package com.orastays.booking.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.booking.bookingserver.entity.BookingVsRoomOraDiscountEntity;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingVsRoomOraDiscountModel;

@Component
public class BookingVsRoomOraDiscountConverter extends CommonConverter
		implements BaseConverter<BookingVsRoomOraDiscountEntity, BookingVsRoomOraDiscountModel> {

	private static final long serialVersionUID = -8557877261668732376L;
	private static final Logger logger = LogManager.getLogger(BookingVsRoomOraDiscountConverter.class);

	@Override
	public BookingVsRoomOraDiscountEntity modelToEntity(BookingVsRoomOraDiscountModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingVsRoomOraDiscountModel entityToModel(BookingVsRoomOraDiscountEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsRoomOraDiscountModel bookingVsRoomOraDiscountModel = new BookingVsRoomOraDiscountModel();
		bookingVsRoomOraDiscountModel = (BookingVsRoomOraDiscountModel) Util.transform(modelMapper, e, bookingVsRoomOraDiscountModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsRoomOraDiscountModel;
	}

	@Override
	public List<BookingVsRoomOraDiscountModel> entityListToModelList(List<BookingVsRoomOraDiscountEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsRoomOraDiscountModel> bookingVsRoomOraDiscountModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsRoomOraDiscountModels = new ArrayList<>();
			for (BookingVsRoomOraDiscountEntity bookingVsRoomOraDiscountEntity : es) {
				bookingVsRoomOraDiscountModels.add(entityToModel(bookingVsRoomOraDiscountEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsRoomOraDiscountModels;
	}

}
