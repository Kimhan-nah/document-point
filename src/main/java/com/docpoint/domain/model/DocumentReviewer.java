package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class DocumentReviewer {
	private final Long id;

	private final WorkingDocument workingDocument;

	private final User reviewer;

	public DocumentReviewer(WorkingDocument workingDocument, User reviewer) {
		this.id = null;
		this.workingDocument = Objects.requireNonNull(workingDocument);
		this.reviewer = Objects.requireNonNull(reviewer);
	}
}
