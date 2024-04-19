package com.docpoint.workingdoc.domain.entity;

import java.util.HashMap;
import java.util.Map;

import com.docpoint.workingdoc.domain.type.ReviewAnswerType;
import com.docpoint.workingdoc.domain.type.ReviewQuestionType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class DocReview {
	private final DocReviewer reviewer;

	private final Map<ReviewQuestionType, ReviewAnswerType> review = new HashMap<>();

	private final boolean isDeleted;

	public DocReview(@NonNull DocReviewer reviewer, boolean isDeleted) {
		this.reviewer = reviewer;
		this.isDeleted = isDeleted;
	}
}
