package com.orastays.booking.bookingserver.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.orastays.booking.bookingserver.helper.MessageUtil;

public class CommonConverter {

	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected MessageUtil messageUtil;
}
