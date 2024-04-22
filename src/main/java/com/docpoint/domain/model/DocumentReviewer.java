package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class DocumentReviewer {
	private final WorkingDocument workingDocument;

	private final User user;

	public DocumentReviewer(WorkingDocument workingDocument, User user) {
		this.workingDocument = Objects.requireNonNull(workingDocument);
		this.user = Objects.requireNonNull(user);
	}
}
