package com.docpoint.adapter.in.dto;

import java.util.List;

import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;

import lombok.Getter;

@Getter
public class WorkingDocumentsWithReviewResponseDto {
	private final List<WorkingDocumentWithReviewDto> workingDocs;
	private final Integer totalPage;

	public WorkingDocumentsWithReviewResponseDto(List<WorkingDocumentWithReviewDto> workingDocs, int totalPage) {
		this.workingDocs = workingDocs;
		this.totalPage = totalPage;
	}

	public static WorkingDocumentsWithReviewResponseDto of(
		List<WorkingDocumentWithReviewDto> workingDocuments, int totalPage) {
		return new WorkingDocumentsWithReviewResponseDto(workingDocuments, totalPage);
	}
}
