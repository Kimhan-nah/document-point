package com.docpoint.domain.model;

import lombok.Getter;

@Getter
public class CpEvaluation {
	private final Long id;
	private final DocumentReviewer documentReviewer;
	private final String comment;
	private final int cp;
	private final boolean isDeleted;

	public CpEvaluation(Long id, DocumentReviewer documentReviewer, String comment, int cp, boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.comment = comment;
		this.cp = cp;
		this.isDeleted = isDeleted;
	}
}
