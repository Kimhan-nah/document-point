package com.docpoint.common.exception;

public class ConflictException extends CustomRuntimeException {
	public ConflictException() {
		super(ErrorCode.CONFLICT);
	}

	public ConflictException(String message) {
		super(ErrorCode.CONFLICT, message);
	}
}
