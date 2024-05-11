package com.docpoint.domain.entity;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CpEvaluation {
	private final Long id;
	private final String comment;
	private final int cp;
	private DocumentReviewer documentReviewer;
	private boolean isDeleted;

	@Builder
	public CpEvaluation(Long id, DocumentReviewer documentReviewer, String comment, int cp, boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.comment = Objects.requireNonNull(comment);
		this.cp = cp;
		this.isDeleted = isDeleted;
	}

	public void updateDocumentReviewer(DocumentReviewer documentReviewer) {
		this.documentReviewer = documentReviewer;
	}

	public void delete() {
		this.isDeleted = true;
	}

	public boolean isDocumentReviewerEmpty() {
		return this.documentReviewer == null;
	}
}
