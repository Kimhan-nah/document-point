package com.docpoint.domain.model;

import lombok.Getter;

@Getter
public class DocumentReviewer {
	private final Long id;

	private final WorkingDocument workingDocument;

	private final User reviewer;

	public DocumentReviewer(Long id, WorkingDocument workingDocument, User reviewer) {
		this.id = id;
		this.workingDocument = workingDocument;
		this.reviewer = reviewer;
	}
}
