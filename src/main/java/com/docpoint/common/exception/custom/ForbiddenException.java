package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorType;

public class ForbiddenException extends CustomRuntimeException {

	public ForbiddenException() {
		super(ErrorType.FORBIDDEN);
	}

	public ForbiddenException(ErrorType errorType) {
		super(errorType);
	}
}
