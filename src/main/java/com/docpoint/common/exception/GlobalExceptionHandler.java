package com.docpoint.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({BindException.class})
	private ResponseEntity<ErrorResponse> validException(BindException ex) {
		log.error("bind error", ex.getBindingResult().getAllErrors().get(0));
		ErrorCode errorCode = ErrorCode.BAD_REQUEST;
		errorCode.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorCode);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler({ForbiddenException.class})
	private ResponseEntity<ErrorResponse> forbiddenException(ForbiddenException ex) {
		log.error("forbidden", ex);
		ErrorResponse response = ErrorResponse.toErrorResponse(ex.getErrorCode());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

}
