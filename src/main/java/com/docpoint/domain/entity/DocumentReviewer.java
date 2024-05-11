package com.docpoint.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DocumentReviewer {
	private final Long id;
	private final WorkingDocument workingDocument;
	private final User reviewer;

	@Builder
	public DocumentReviewer(Long id, WorkingDocument workingDocument, User reviewer) {
		this.id = id;
		this.workingDocument = workingDocument;
		this.reviewer = reviewer;
	}

	public boolean isWorkingDocumentEmpty() {
		return this.workingDocument == null;
	}

	public boolean isReviewerEmpty() {
		return this.reviewer == null;
	}
}
