package com.docpoint.domain.entity;

import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Review {
	private final Long id;

	private final DocumentReviewer documentReviewer;

	private final ReviewQuestionType question;

	private final ReviewAnswerType answer;

	private final boolean isDeleted;

	@Builder
	public Review(Long id, DocumentReviewer documentReviewer, ReviewQuestionType question, ReviewAnswerType answer,
		boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.question = question;
		this.answer = answer;
		this.isDeleted = isDeleted;
	}

	public Review updateDocumentReviewer(DocumentReviewer documentReviewer) {
		return new Review(this.id, documentReviewer, this.question, this.answer, this.isDeleted);
	}

	public boolean isDocumentReviewerEmpty() {
		return this.documentReviewer == null;
	}
}
