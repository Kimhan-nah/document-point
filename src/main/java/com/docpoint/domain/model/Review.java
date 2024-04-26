package com.docpoint.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
		this.documentReviewer = Objects.requireNonNull(documentReviewer);
		this.isDeleted = isDeleted;
	}
}
