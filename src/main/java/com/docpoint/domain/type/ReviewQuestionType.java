package com.docpoint.domain.type;

public enum ReviewQuestionType {
	/**
	 * '명확성', '일관성', '완성도;
	 */
	CLARITY("명확성"), CONSISTENCY("일관성"), COMPLETENESS("완성도");

	private final String question;

	ReviewQuestionType(String question) {
		this.question = question;
	}
}
