package com.docpoint.common.exception;

class ErrorResponse {
	private int status;
	private String message;

	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public static ErrorResponse toErrorResponse(ErrorCode errorCode) {
		return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
	}
}
