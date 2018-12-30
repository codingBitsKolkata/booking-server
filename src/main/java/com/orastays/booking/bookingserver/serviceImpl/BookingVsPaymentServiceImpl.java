package com.orastays.booking.bookingserver.serviceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orastays.booking.bookingserver.dao.BookingVsPaymentDAO;
import com.orastays.booking.bookingserver.entity.BookingVsPaymentEntity;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.service.BookingVsPaymentService;

@Service
@Transactional
public class BookingVsPaymentServiceImpl implements BookingVsPaymentService{

	private static final Logger logger = LogManager.getLogger(BookingVsPaymentServiceImpl.class);
	
	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Override
	public BookingVsPaymentEntity getBookingVsPaymentEntityByOrderId(String orderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getBookingVsPaymentEntityByOrderId -- Start");
		}
		
		BookingVsPaymentEntity bookingVsPaymentEntity = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("orderId", orderId);
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".ConvenienceEntity", outerMap1);

			bookingVsPaymentEntity = bookingVsPaymentDAO.fetchObjectBySubCiteria(alliasMap);
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getActiveConvenienceEntity -- " + Util.errorToString(e));
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getBookingVsPaymentEntityByOrderId -- END");
		}
		
		return bookingVsPaymentEntity;
	}

}
