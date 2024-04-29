package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorType;

public class ConflictException extends CustomRuntimeException {
	public ConflictException() {
		super(ErrorType.CONFLICT);
	}

	public ConflictException(ErrorType errorType) {
		super(errorType);
	}
}
