package com.money.transfer.exception;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { BindException.class, MethodArgumentNotValidException.class,
			MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<GeneralError> handleValidationException(HttpServletRequest req, Exception ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<GeneralError>(new GeneralError(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Throwable.class })
	protected ResponseEntity<GeneralError> handleConflict(HttpServletRequest request, Exception ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<GeneralError>(new GeneralError(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<APIError> handleConflict(HttpServletRequest request, BusinessException ex) {
		log.error("[" + ex.getErrorCode() + "] " + ex.getLocalizedMessage());
		return new ResponseEntity<APIError>(new APIError(ex.getErrorCode(), ex.getArguments(), ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	private static class GeneralError implements Serializable {

		private static final long serialVersionUID = 1L;

		private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		private String debugMessage;

		public GeneralError(String debugMessage) {
			this.debugMessage = debugMessage;
		}

		public Timestamp getTimestamp() {
			return timestamp;
		}

		public String getDebugMessage() {
			return debugMessage;
		}


	}

	private static class APIError extends GeneralError implements Serializable {

		private static final long serialVersionUID = 1L;

		private String errorCode;
		private Object[] arguments;

		public APIError(String errorCode, Object[] arguments, String debugMessage) {
			super(debugMessage);
			this.errorCode = errorCode;
			this.arguments = arguments;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public Object[] getArguments() {
			return arguments;
		}

	}
}
