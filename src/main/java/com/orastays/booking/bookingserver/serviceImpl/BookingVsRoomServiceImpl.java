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

import com.orastays.booking.bookingserver.dao.BookingVsRoomDAO;
import com.orastays.booking.bookingserver.entity.BookingVsRoomEntity;
import com.orastays.booking.bookingserver.helper.RoomStatus;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.service.BookingVsRoomService;

@Service
@Transactional
public class BookingVsRoomServiceImpl implements BookingVsRoomService {
	
	private static final Logger logger = LogManager.getLogger(BookingVsRoomServiceImpl.class);
	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;

	@Override
	public boolean checkIfPrivateRoomIsBooked(String roomId) {
		if (logger.isInfoEnabled()) {
			logger.info("checkIfPrivateRoomIsBooked -- START");
		}
		List<BookingVsRoomEntity> bookingVsRoomEntities = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("roomId", roomId);
			innerMap1.put("status", String.valueOf(RoomStatus.BOOKED.ordinal()));
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".BookingVsRoomEntity", outerMap1);

			bookingVsRoomEntities = bookingVsRoomDAO.fetchListBySubCiteria(alliasMap);
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getActiveConvenienceEntity -- " + Util.errorToString(e));
			}
		}
		if(bookingVsRoomEntities != null && bookingVsRoomEntities.size() > 0) {
			//room already booked
			return true;
		}


		if (logger.isInfoEnabled()) {
			logger.info("checkIfPrivateRoomIsBooked -- END");
		}

		return false;
	}

	@Override
	public boolean checkIfSharedRoomIsBooked(String roomId) {
		if (logger.isInfoEnabled()) {
			logger.info("checkIfSharedRoomIsBooked -- START");
		}

		if (logger.isInfoEnabled()) {
			logger.info("checkIfSharedRoomIsBooked -- END");
		}
		return false;
	}

}
