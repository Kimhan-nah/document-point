package com.docpoint.infrastructure.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.docpoint.common.exception.ErrorResponse;
import com.docpoint.common.exception.ErrorType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		ResponseEntity<ErrorResponse> errorResponse = ResponseEntity
			.status(HttpStatus.UNAUTHORIZED)
			.body(ErrorResponse.toErrorResponse(ErrorType.UNAUTHORIZED));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse.getBody()));
	}
}
