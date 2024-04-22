package com.docpoint.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import lombok.Getter;

@Getter
public class DocumentReview {
	private final DocumentReviewer reviewer;

	private final Map<ReviewQuestionType, ReviewAnswerType> review = new HashMap<>();

	private final boolean isDeleted;

	public DocumentReview(DocumentReviewer reviewer, boolean isDeleted) {
		this.reviewer = Objects.requireNonNull(reviewer);
		this.isDeleted = isDeleted;
	}
}
