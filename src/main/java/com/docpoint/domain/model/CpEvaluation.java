package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class CpEvaluation {
	private final Long id;

	private final WorkingDocument workingDocument;

	private final String comment;

	private final int cp;

	private final boolean isDeleted;

	public CpEvaluation(WorkingDocument workingDocument, String comment, int cp, boolean isDeleted) {
		this.id = null;
		this.workingDocument = Objects.requireNonNull(workingDocument);
		this.comment = Objects.requireNonNull(comment);
		this.cp = cp;
		this.isDeleted = isDeleted;
	}
}
