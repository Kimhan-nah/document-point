package com.docpoint.adapter.in.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class WorkingDocumentsResponseDto {
	private final List<WorkingDocumentDto> workingDocuments;
	private final int totalPage;

	public WorkingDocumentsResponseDto(List<WorkingDocumentDto> workingDocuments, int totalPage) {
		this.workingDocuments = workingDocuments;
		this.totalPage = totalPage;
	}

	public static WorkingDocumentsResponseDto of(List<WorkingDocumentDto> workingDocuments, int totalPage) {
		return new WorkingDocumentsResponseDto(workingDocuments, totalPage);
	}
}
