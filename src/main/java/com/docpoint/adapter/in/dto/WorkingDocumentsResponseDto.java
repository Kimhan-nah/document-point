package com.docpoint.adapter.in.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class WorkingDocumentsResponseDto {
	private final List<WorkingDocumentDto> workingDocs;
	private final int totalPage;

	public WorkingDocumentsResponseDto(List<WorkingDocumentDto> workingDocs, int totalPage) {
		this.workingDocs = workingDocs;
		this.totalPage = totalPage;
	}

	public static WorkingDocumentsResponseDto of(List<WorkingDocumentDto> workingDocuments, int totalPage) {
		return new WorkingDocumentsResponseDto(workingDocuments, totalPage);
	}
}
