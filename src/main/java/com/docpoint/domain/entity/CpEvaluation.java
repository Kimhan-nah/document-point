package com.docpoint.domain.entity;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CpEvaluation {
	private final Long id;
	private final DocumentReviewer documentReviewer;
	private final String comment;
	private final int cp;
	private final boolean isDeleted;

	@Builder
	public CpEvaluation(Long id, DocumentReviewer documentReviewer, String comment, int cp, boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.comment = Objects.requireNonNull(comment);
		this.cp = cp;
		this.isDeleted = isDeleted;
	}

	public CpEvaluation updateDocumentReviewer(DocumentReviewer documentReviewer) {
		return CpEvaluation.builder()
			.id(this.id)
			.documentReviewer(documentReviewer)
			.comment(this.comment)
			.cp(this.cp)
			.isDeleted(this.isDeleted)
			.build();
	}

	public boolean isDocumentReviewerEmpty() {
		return this.documentReviewer == null;
	}
}
