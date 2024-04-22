package com.docpoint.domain.type;

public enum DocStatusType {
	/**
	 * 검토중, 승인 요청, 승인 완료
	 */
	REVIEW("검토중"), APPROVAL_REQUEST("승인 요청"), APPROVED("승인 완료");

	private final String docStatusType;

	DocStatusType(String docStatusType) {
		this.docStatusType = docStatusType;
	}
}
