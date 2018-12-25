package com.orastays.booking.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.booking.bookingserver.entity.BookingPriceEntity;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingPriceModel;

@Component
public class BookingPriceConverter extends CommonConverter
		implements BaseConverter<BookingPriceEntity, BookingPriceModel> {

	private static final long serialVersionUID = -9102357208519258911L;
	private static final Logger logger = LogManager.getLogger(BookingVsPaymentConverter.class);

	@Override
	public BookingPriceModel entityToModel(BookingPriceEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingPriceModel bookingPriceModel = new BookingPriceModel();
		bookingPriceModel = (BookingPriceModel) Util.transform(modelMapper, e, bookingPriceModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingPriceModel;
	}

	@Override
	public List<BookingPriceModel> entityListToModelList(List<BookingPriceEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingPriceModel> bookingPriceModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingPriceModels = new ArrayList<>();
			for (BookingPriceEntity bookingPriceEntity : es) {
				bookingPriceModels.add(entityToModel(bookingPriceEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingPriceModels;
	}

	@Override
	public BookingPriceEntity modelToEntity(BookingPriceModel m) {
		// TODO Auto-generated method stub
		return null;
	}
}
