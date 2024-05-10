package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkingDocumentWithReviewDto {
	// working document
	private Long id;
	private String title;
	private String content;
	private DocStatusType status;
	private DocType docType;
	private String link;
	private boolean isDeleted;

	// review
	private boolean isReviewed;

	@Builder
	public WorkingDocumentWithReviewDto(Long id, String title, String content, DocStatusType status, DocType docType,
		String link, boolean isDeleted, boolean isReviewed) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.docType = docType;
		this.link = link;
		this.isDeleted = isDeleted;
		this.isReviewed = isReviewed;
	}

	public static WorkingDocumentWithReviewDto toDto(WorkingDocument workingDocument, boolean isReviewed) {
		return WorkingDocumentWithReviewDto.builder()
			.id(workingDocument.getId())
			.title(workingDocument.getTitle())
			.content(workingDocument.getContent())
			.status(workingDocument.getStatus())
			.docType(workingDocument.getDocType())
			.link(workingDocument.getLink())
			.isDeleted(workingDocument.isDeleted())
			.isReviewed(isReviewed)
			.build();
	}

	public static WorkingDocumentWithReviewDto toDto(WorkingDocument workingDocument) {
		return WorkingDocumentWithReviewDto.builder()
			.id(workingDocument.getId())
			.title(workingDocument.getTitle())
			.content(workingDocument.getContent())
			.status(workingDocument.getStatus())
			.docType(workingDocument.getDocType())
			.link(workingDocument.getLink())
			.isDeleted(workingDocument.isDeleted())
			.isReviewed(false)
			.build();
	}
}
