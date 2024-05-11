package com.docpoint.domain.entity;

import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Review {
	private final Long id;
	private final ReviewQuestionType question;
	private final ReviewAnswerType answer;
	private final boolean isDeleted;
	private DocumentReviewer documentReviewer;

	@Builder
	public Review(Long id, DocumentReviewer documentReviewer, ReviewQuestionType question, ReviewAnswerType answer,
		boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.question = question;
		this.answer = answer;
		this.isDeleted = isDeleted;
	}

	public void updateDocumentReviewer(DocumentReviewer documentReviewer) {
		this.documentReviewer = documentReviewer;
	}

	public boolean isDocumentReviewerEmpty() {
		return this.documentReviewer == null;
	}
}
