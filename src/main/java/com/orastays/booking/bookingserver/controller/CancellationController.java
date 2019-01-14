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
import com.orastays.booking.bookingserver.service.CancellationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Booking", tags = "Booking")
public class CancellationController extends BaseController {

	private static final Logger logger = LogManager.getLogger(CancellationController.class);

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected CancellationService cancellationService;

	@PostMapping(value = "/cancel-booking", produces = "application/json")
	@ApiOperation(value = "Cancel Booking", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1802, message = "Booking has already been cancelled"),
			@ApiResponse(code = 1803, message = "Booking has not been confirmed yet.Booking cannot be cancelled"),
			@ApiResponse(code = 1804, message = "Booking not found"),
			@ApiResponse(code = 1805, message = "Refund Failed. Please try again later")})

	public ResponseEntity<ResponseModel> cancelBooking(@RequestBody BookingModel bookingModel) {
		if (logger.isInfoEnabled()) {
			logger.info("cancelBooking -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(bookingModel, AuthConstant.INCOMING, "cancel-booking", request);
		try {
			cancellationService.cancelBooking(bookingModel);
			responseModel.setResponseBody(messageUtil.getBundle(AuthConstant.CANCELLATION_SUCCESS_MESSAGE));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in cancel-booking -- " + Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Cancel Booking -- " + Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "cancel-booking", request);

		if (logger.isInfoEnabled()) {
			logger.info("cancelBooking -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/get-property-cancellations", produces = "application/json")
	@ApiOperation(value = "Property Cancellations", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })

	public ResponseEntity<ResponseModel> getPropertyCancellations(@RequestBody BookingModel bookingModel) {
		if (logger.isInfoEnabled()) {
			logger.info("getPropertyCancellations -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(bookingModel, AuthConstant.INCOMING, "get-property-cancellations", request);
		try {
			List<BookingModel> bookingModels = cancellationService.getPropertyCancellations(bookingModel);
			responseModel.setResponseBody(bookingModels);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in getPropertyCancellations -- " + Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getPropertyCancellations -- " + Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "get-property-cancellations", request);

		if (logger.isInfoEnabled()) {
			logger.info("getPropertyBookings -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value = "/get-user-cancellations", produces = "application/json")
	@ApiOperation(value = "User Cancellations", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })

	public ResponseEntity<ResponseModel> getUserCancellations(@RequestBody BookingModel bookingModel) {
		if (logger.isInfoEnabled()) {
			logger.info("getUserCancellations -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(bookingModel, AuthConstant.INCOMING, "get-user-cancellations", request);
		try {
			List<BookingModel> bookingModels = cancellationService.getUserCancellations(bookingModel);
			responseModel.setResponseBody(bookingModels);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in getUserCancellations -- " + Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getUserCancellations -- " + Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "get-user-cancellations", request);

		if (logger.isInfoEnabled()) {
			logger.info("getUserCancellations -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}

}
