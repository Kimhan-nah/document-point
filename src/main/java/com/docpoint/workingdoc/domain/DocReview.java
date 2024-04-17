package com.docpoint.workingdoc.domain;

import java.util.HashMap;
import java.util.Map;

import com.docpoint.workingdoc.domain.type.ReviewAnswerType;
import com.docpoint.workingdoc.domain.type.ReviewQuestionType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class DocReview {
	@NonNull
	private final DocReviewer reviewer;

	@NonNull
	private final Map<ReviewQuestionType, ReviewAnswerType> review = new HashMap<>();

	private final boolean isDeleted;

	public DocReview(@NonNull DocReviewer reviewer, boolean isDeleted) {
		this.reviewer = reviewer;
		this.isDeleted = isDeleted;
	}
}
