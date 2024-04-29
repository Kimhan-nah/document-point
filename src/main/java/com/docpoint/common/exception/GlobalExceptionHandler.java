package com.docpoint.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.docpoint.common.exception.custom.ForbiddenException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({BindException.class})
	private ResponseEntity<ErrorResponse> validException(BindException ex) {
		log.error("bind error", ex.getBindingResult().getAllErrors().get(0));
		ErrorType errorType = ErrorType.BAD_REQUEST;
		errorType.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler({ForbiddenException.class})
	private ResponseEntity<ErrorResponse> forbiddenException(ForbiddenException ex) {
		log.error("forbidden", ex);
		ErrorResponse response = ErrorResponse.toErrorResponse(ex.getErrorType());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

}
