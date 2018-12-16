package com.orastays.booking.bookingserver.controller;

import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.booking.bookingserver.exceptions.FormExceptions;
import com.orastays.booking.bookingserver.helper.AuthConstant;
import com.orastays.booking.bookingserver.helper.MessageUtil;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.model.BookingModel;
import com.orastays.booking.bookingserver.model.ResponseModel;
import com.orastays.booking.bookingserver.service.BookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Booking", tags = "Booking")
public class BookingController extends BaseController {
	
	private static final Logger logger = LogManager.getLogger(BookingController.class);
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected BookingService bookingService;
	
	@GetMapping(value = "/add-booking", produces = "application/json")
	@ApiOperation(value = "Add Booking", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 312, message = "Please provide UserID"),
			@ApiResponse(code = 313, message = "Invalid UserID"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token")})
	
	public ResponseEntity<ResponseModel> addBooking() {
		return null;
	}
	
	
	@PostMapping(value = "/get-bookings", produces = "application/json")
	@ApiOperation(value = "Add Booking", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 312, message = "Please provide UserID"),
			@ApiResponse(code = 313, message = "Invalid UserID"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token")})
	
	public ResponseEntity<ResponseModel> getBookings(@RequestBody BookingModel bookingModel) {
		if (logger.isInfoEnabled()) {
			logger.info("getBookings -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(bookingModel, AuthConstant.INCOMING, "Get Booking", request);
		try {
			List<BookingModel> bookingModels = bookingService.getBookings(bookingModel);
			responseModel.setResponseBody(bookingModels);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("otp.send.success"));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Get Bookings -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Get Bookings -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Get Bookings", request);

		if (logger.isInfoEnabled()) {
			logger.info("getBookings -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value = "/validate-booking", produces = "application/json")
	@ApiOperation(value = "Validate Booking", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })
	
	public ResponseEntity<ResponseModel> validateBooking(@RequestBody BookingModel bookingModel) {
		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(bookingModel, AuthConstant.INCOMING, "Validate Booking", request);
		try {
			BookingModel bookingModel2 = bookingService.validateBooking(bookingModel);
			responseModel.setResponseBody(bookingModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("otp.send.success"));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Validate Booking -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Validate Booking -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Validate Booking", request);

		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
