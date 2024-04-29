package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorType;

public class NotFoundException extends CustomRuntimeException {

	public NotFoundException() {
		super(ErrorType.NOT_FOUND);
	}

	public NotFoundException(ErrorType errorType) {
		super(errorType);
	}
}
