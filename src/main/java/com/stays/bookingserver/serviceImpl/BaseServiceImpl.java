package com.stays.bookingserver.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.stays.bookingserver.converter.BookingConverter;
import com.stays.bookingserver.dao.BookingDAO;
import com.stays.bookingserver.utils.BookingUtil;

public class BaseServiceImpl {
	@Autowired
	protected BookingUtil bookingUtil;

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingConverter bookingConverter;
}
