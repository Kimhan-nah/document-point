package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorType;

public class BadRequestException extends CustomRuntimeException {

	public BadRequestException() {
		super(ErrorType.BAD_REQUEST);
	}

	public BadRequestException(ErrorType errorType) {
		super(errorType);
	}
}
