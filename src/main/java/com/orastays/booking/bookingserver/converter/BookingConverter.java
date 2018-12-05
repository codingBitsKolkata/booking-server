package com.orastays.booking.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingModel;


@Component
public class BookingConverter extends CommonConverter implements BaseConverter<BookingEntity, BookingModel> {

	private static final long serialVersionUID = 754163973023221139L;
	private static final Logger logger = LogManager.getLogger(BookingConverter.class);

	@Override
	public BookingEntity modelToEntity(BookingModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingModel entityToModel(BookingEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingModel bookingModel = new BookingModel();
		bookingModel = (BookingModel) Util.transform(modelMapper, e, bookingModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingModel;
	}

	@Override
	public List<BookingModel> entityListToModelList(List<BookingEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingModel> bookingModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingModels = new ArrayList<>();
			for (BookingEntity bookingEntity : es) {
				bookingModels.add(entityToModel(bookingEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingModels;
	}
}