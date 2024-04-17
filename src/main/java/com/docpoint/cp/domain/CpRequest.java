package com.docpoint.cp.domain;

import com.docpoint.workingdoc.domain.WorkingDoc;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class CpRequest {
	@NonNull
	private final WorkingDoc workingDoc;

	private final int requestCp;

	private final boolean isDeleted;

	public CpRequest(WorkingDoc workingDoc, int requestCp, boolean isDeleted) {
		this.workingDoc = workingDoc;
		this.requestCp = requestCp;
		this.isDeleted = isDeleted;
	}
}
