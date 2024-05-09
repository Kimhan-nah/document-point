package com.docpoint.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> validException(BindException ex) {
		log.error("bind error", ex.getBindingResult().getAllErrors().get(0));
		ErrorType errorType = ErrorType.BAD_REQUEST;
		errorType.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> forbiddenException(ForbiddenException ex) {
		log.error("forbidden", ex);
		ErrorResponse response = ErrorResponse.toErrorResponse(ex.getErrorType());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestException(BadRequestException ex) {
		log.error("bad request", ex);
		ErrorResponse response = ErrorResponse.toErrorResponse(ex.getErrorType());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> conflictException(ConflictException ex) {
		log.error("conflict", ex);
		ErrorResponse response = ErrorResponse.toErrorResponse(ex.getErrorType());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex) {
		log.error("not found", ex);
		ErrorResponse response = ErrorResponse.toErrorResponse(ex.getErrorType());
		// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exception(Exception ex) {
		log.error("exception", ex);
		ErrorType errorType = ErrorType.INTERNAL_SERVER_ERR;
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runtimeException(RuntimeException ex) {
		log.error("runtime exception", ex);
		ErrorType errorType = ErrorType.INTERNAL_SERVER_ERR;
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> missingServletRequestParameterException(
		MissingServletRequestParameterException ex) {
		log.error("missing servlet request parameter exception", ex);
		ErrorType errorType = ErrorType.BAD_REQUEST;
		errorType.setMessage(ex.getMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException ex) {
		log.error("http request method not supported exception", ex);
		ErrorType errorType = ErrorType.METHOD_NOT_ALLOWED;
		errorType.setMessage(ex.getMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponse> noResourceFoundException(NoResourceFoundException ex) {
		log.error("no resource found exception", ex);
		ErrorType errorType = ErrorType.NOT_FOUND;
		errorType.setMessage(ex.getMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("http message not readable exception", ex);
		ErrorType errorType = ErrorType.BAD_REQUEST;
		errorType.setMessage(ex.getMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> validationException(ValidationException ex) {
		log.error("validation exception", ex);
		ErrorType errorType = ErrorType.BAD_REQUEST;
		errorType.setMessage(ex.getMessage());
		ErrorResponse response = ErrorResponse.toErrorResponse(errorType);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
