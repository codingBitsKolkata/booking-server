package com.orastays.booking.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.booking.bookingserver.entity.BookingVsHomestayEntity;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingVsHomestayModel;


@Component
public class BookingVsHomestayConverter extends CommonConverter
		implements BaseConverter<BookingVsHomestayEntity, BookingVsHomestayModel> {

	private static final long serialVersionUID = -8173291928742648696L;
	private static final Logger logger = LogManager.getLogger(BookingVsHomestayConverter.class);

	@Override
	public BookingVsHomestayEntity modelToEntity(BookingVsHomestayModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingVsHomestayModel entityToModel(BookingVsHomestayEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsHomestayModel bookingVsHomestayModel = new BookingVsHomestayModel();
		bookingVsHomestayModel = (BookingVsHomestayModel) Util.transform(modelMapper, e, bookingVsHomestayModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsHomestayModel;
	}

	@Override
	public List<BookingVsHomestayModel> entityListToModelList(List<BookingVsHomestayEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsHomestayModel> bookingVsHomestayModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsHomestayModels = new ArrayList<>();
			for (BookingVsHomestayEntity bookingVsHomestayEntity : es) {
				bookingVsHomestayModels.add(entityToModel(bookingVsHomestayEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsHomestayModels;
	}

}
