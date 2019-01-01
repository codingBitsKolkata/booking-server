package com.orastays.booking.bookingserver.serviceImpl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.service.CancellationService;
import com.orastays.booking.bookingserver.validations.CancellationValidation;

@Service
@Transactional
public class CancellationServiceImpl implements CancellationService{

	private static final Logger logger = LogManager.getLogger(CancellationService.class);
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected CancellationValidation cancellationValidation;
	
	@Override
	public void cancelBooking(BookingModel bookingModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("cancelBooking -- START");
		}
		cancellationValidation.validateCancelBooking(bookingModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("cancelBooking -- END");
		}
		
	}


}
