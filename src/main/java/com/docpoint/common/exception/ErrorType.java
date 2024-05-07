package com.docpoint.common.exception;

import static com.docpoint.common.exception.ErrorCode.*;

import lombok.Getter;

@Getter
public enum ErrorType {
	// 5xx Server Error
	BUSINESS_EXCEPTION(500, E500000, E500000.getMessage()),
	NULL_POINT(500, E500001, E500001.getMessage()),
	INTERNAL_SERVER_ERR(500, E500002, E500002.getMessage()),

	// 4xx Client Error
	BAD_REQUEST(400, E400000, E400000.getMessage()),
	UNAUTHORIZED(401, E401000, E401000.getMessage()),
	FORBIDDEN(403, E403000, E403000.getMessage()),
	NOT_FOUND(404, E404000, E404000.getMessage()),
	CONFLICT(409, E409000, E409000.getMessage()),

	// 400 Bad Request Error
	BAD_WORKING_STATUS(400, E400001, E400001.getMessage()),
	BAD_WORKING_DOCUMENT_STATUS(400, E400002, E400002.getMessage()),
	INVALID_REVIEWER(400, E400003, E400003.getMessage()),

	// 403 Forbidden Error
	FORBIDDEN_REVIEWER(403, E403001, E403001.getMessage()),
	FORBIDDEN_ASSIGNEE(403, E403002, E403002.getMessage()),

	// 404 Not Found Error
	NOT_FOUND_CP(404, E404001, E404001.getMessage()),
	NOT_FOUND_WORKING_DOCUMENT(404, E404002, E404002.getMessage()),
	NOT_FOUND_TEAM(404, E404003, E404003.getMessage()),
	DELETED_WORKING(404, E404004, E404004.getMessage()),
	DELETED_TEAM(404, E404005, E404005.getMessage()),
	DELETED_WORKING_DOCUMENT(404, E404006, E404006.getMessage()),
	NOT_FOUND_REVIEW(404, E404007, E404007.getMessage()),
	DELETED_REVIEW(404, E404008, E404008.getMessage()),
	NOT_FOUND_WORKING(404, E404009, E404009.getMessage()),
	NOT_FOUND_USER(404, E404010, E404010.getMessage()),

	// 409 Conflict Error
	CONFLICT_DOCUMENT_STATUS(409, E409001, E409001.getMessage()),
	CONFLICT_CP_EVALUATION(409, E409002, E409002.getMessage()),

	;

	private final int status;
	private ErrorCode errorCode;
	private String message;

	ErrorType(int status, ErrorCode errorCode, String message) {
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
