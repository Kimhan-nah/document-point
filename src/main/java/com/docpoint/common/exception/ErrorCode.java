package com.docpoint.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	// 5xx Server Error
	E500000("INTERNAL SERVER ERROR"),
	E500001("NULL POINT ERROR"),
	E500002("BUSINESS ERROR"),

	// 400 Bad Request Error
	E400000("BAD REQUEST"),
	E400001("BAD WORKING STATUS"),
	E400002("BAD WORKING DOCUMENT STATUS"),

	// 401 Unauthorized Error
	E401000("UNAUTHORIZED"),

	// 403 Forbidden Error
	E403000("FORBIDDEN"),
	E403001("You are not REVIEWER of this WORKING DOCUMENT"),
	E403002("You are not ASSIGNEE of this WORKING"),

	// 404 Not Found Error
	E404000("NOT FOUND"),
	E404001("NOT FOUND CP"),
	E404002("NOT FOUND WORKING DOCUMENT"),
	E404003("NOT FOUND TEAM"),
	E404004("DELETED WORKING"),
	E404005("DELETED TEAM"),
	E404006("DELETED WORKING DOCUMENT"),
	E404007("NOT FOUND REVIEW"),
	E404008("DELETED REVIEW"),

	// 409 Conflict Error
	E409000("CONFLICT"),
	E409001("CONFLICT DOCUMENT STATUS"),

	;
	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}
}
