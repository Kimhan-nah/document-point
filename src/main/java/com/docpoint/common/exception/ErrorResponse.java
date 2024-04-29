package com.docpoint.common.exception;

class ErrorResponse {
	private int status;
	private ErrorCode errorCode;
	private String message;

	private ErrorResponse(int status, ErrorCode errorCode, String message) {
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}

	public static ErrorResponse toErrorResponse(ErrorType errorType) {
		return new ErrorResponse(errorType.getStatus(), errorType.getErrorCode(), errorType.getMessage());
	}
}
