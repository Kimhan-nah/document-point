package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorCode;

public class ForbiddenException extends CustomRuntimeException {

	public ForbiddenException() {
		super(ErrorCode.FORBIDDEN);
	}

	public ForbiddenException(String message) {
		super(ErrorCode.FORBIDDEN, message);
	}
}
