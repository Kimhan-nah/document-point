package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class CpRequest {
	private final WorkingDocument workingDocument;

	private final int requestCp;

	private final boolean isDeleted;

	public CpRequest(WorkingDocument workingDocument, int requestCp, boolean isDeleted) {
		this.workingDocument = Objects.requireNonNull(workingDocument);
		this.requestCp = requestCp;
		this.isDeleted = isDeleted;
	}
}
