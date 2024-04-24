package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorCode;

public class ConflictException extends CustomRuntimeException {
	public ConflictException() {
		super(ErrorCode.CONFLICT);
	}

	public ConflictException(String message) {
		super(ErrorCode.CONFLICT, message);
	}
}
