package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorCode;

public class BadRequestException extends CustomRuntimeException {

	public BadRequestException() {
		super(ErrorCode.BAD_REQUEST);
	}

	public BadRequestException(String message) {
		super(ErrorCode.BAD_REQUEST, message);
	}
}
