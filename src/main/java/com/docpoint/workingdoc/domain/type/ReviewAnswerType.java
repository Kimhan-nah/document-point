package com.docpoint.workingdoc.domain.type;

public enum ReviewAnswerType {
	/**
	 * '좋아요', '보통', '아쉬워요'
	 */
	GOOD("좋아요"), NORMAL("보통"), BAD("아쉬워요");

	private final String answer;

	ReviewAnswerType(String answer) {
		this.answer = answer;
	}
}
