package com.orastays.booking.bookingserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.orastays.booking.bookingserver.helper.MessageUtil;

public class BaseController {

		@Autowired
		protected HttpServletRequest request;
		
		@Autowired
		protected HttpServletResponse response;
		
		@Autowired
		protected MessageUtil messageUtil;
}
