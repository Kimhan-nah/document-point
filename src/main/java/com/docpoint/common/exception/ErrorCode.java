package com.docpoint.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	// 5xx Server Error
	BUSINESS_EXCEPTION(500, "BUSINESS ERROR"),
	NULL_POINT(500, "NULL POINT ERROR"),
	INTERNAL_SERVER_ERR(500, "INTERNAL SERVER ERROR"),

	// 4xx Client Error
	BAD_REQUEST(400, "BAD REQUEST"),
	UNAUTHORIZED(401, "UNAUTHORIZED"),
	FORBIDDEN(403, "FORBIDDEN"),
	NOT_FOUND(404, "NOT FOUND"),
	CONFLICT(409, "CONFLICT");

	private final int status;
	private String message;

	ErrorCode(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
