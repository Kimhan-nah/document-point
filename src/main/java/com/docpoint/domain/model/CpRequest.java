package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class CpRequest {
	private final Long id;

	private final WorkingDocument workingDocument;

	private final int requestCp;

	private final boolean isDeleted;

	public CpRequest(WorkingDocument workingDocument, int requestCp, boolean isDeleted) {
		this.id = null;
		this.workingDocument = Objects.requireNonNull(workingDocument);
		this.requestCp = requestCp;
		this.isDeleted = isDeleted;
	}
}
