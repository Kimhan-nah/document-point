package com.docpoint.domain.entity;

import java.util.HashMap;
import java.util.Map;

import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import lombok.Getter;

@Getter
public class Review {
	private final Long id;

	private final DocumentReviewer documentReviewer;

	private final Map<ReviewQuestionType, ReviewAnswerType> review = new HashMap<>();

	private final boolean isDeleted;

	public Review(DocumentReviewer documentReviewer, boolean isDeleted) {
		this.id = null;
		this.documentReviewer = documentReviewer;
		this.isDeleted = isDeleted;
	}

	public Review(Long id, DocumentReviewer documentReviewer, boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.isDeleted = isDeleted;
	}

	public Review updateDocumentReviewer(DocumentReviewer documentReviewer) {
		return new Review(this.id, documentReviewer, this.isDeleted);
	}
}
