package com.orastays.booking.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingVsRoomModel;

@Component
public class BookingVsRoomConverter extends CommonConverter
		implements BaseConverter<BookingVsRoomEntity, BookingVsRoomModel> {

	private static final long serialVersionUID = -6543158351432501780L;
	private static final Logger logger = LogManager.getLogger(BookingVsRoomConverter.class);

	@Override
	public BookingVsRoomEntity modelToEntity(BookingVsRoomModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingVsRoomModel entityToModel(BookingVsRoomEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsRoomModel bookingVsRoomModel = new BookingVsRoomModel();
		bookingVsRoomModel = (BookingVsRoomModel) Util.transform(modelMapper, e, bookingVsRoomModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsRoomModel;
	}

	@Override
	public List<BookingVsRoomModel> entityListToModelList(List<BookingVsRoomEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsRoomModel> bookingVsRoomModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsRoomModels = new ArrayList<>();
			for (BookingVsRoomEntity bookingVsRoomEntity : es) {
				bookingVsRoomModels.add(entityToModel(bookingVsRoomEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsRoomModels;
	}

}
