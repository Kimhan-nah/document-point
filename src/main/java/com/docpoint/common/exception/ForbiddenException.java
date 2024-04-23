package com.docpoint.common.exception;

public class ForbiddenException extends CustomRuntimeException {

	public ForbiddenException() {
		super(ErrorCode.FORBIDDEN);
	}

	public ForbiddenException(String message) {
		super(ErrorCode.FORBIDDEN, message);
	}
}
