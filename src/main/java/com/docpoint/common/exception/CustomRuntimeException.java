package com.docpoint.common.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
	private ErrorCode errorCode;

	public CustomRuntimeException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public CustomRuntimeException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		errorCode.setMessage(message);
	}
}
