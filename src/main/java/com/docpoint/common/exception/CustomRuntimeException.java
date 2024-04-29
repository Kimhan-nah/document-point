package com.docpoint.common.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
	private ErrorType errorType;

	public CustomRuntimeException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
