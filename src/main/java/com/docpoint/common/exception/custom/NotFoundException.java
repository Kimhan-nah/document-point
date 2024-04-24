package com.docpoint.common.exception.custom;

import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorCode;

public class NotFoundException extends CustomRuntimeException {

	public NotFoundException() {
		super(ErrorCode.NOT_FOUND);
	}

	public NotFoundException(String message) {
		super(ErrorCode.NOT_FOUND, message);
	}

}
