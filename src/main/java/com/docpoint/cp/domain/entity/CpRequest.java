package com.docpoint.cp.domain.entity;

import com.docpoint.workingdoc.domain.entity.WorkingDoc;

import lombok.Getter;

@Getter
public class CpRequest {
	private final WorkingDoc workingDoc;

	private final int requestCp;

	private final boolean isDeleted;

	public CpRequest(WorkingDoc workingDoc, int requestCp, boolean isDeleted) {
		this.workingDoc = workingDoc;
		this.requestCp = requestCp;
		this.isDeleted = isDeleted;
	}
}
