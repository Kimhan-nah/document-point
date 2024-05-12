package com.docpoint.adapter.in.dto;

import java.time.LocalDateTime;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkingDocumentWithReviewDto {
	// working document
	private Long id;
	private String title;
	private String assigneeName;
	private LocalDateTime registerDate;
	private DocStatusType status;
	private String link;

	// review
	private boolean isReviewed;

	@Builder
	public WorkingDocumentWithReviewDto(Long id, String title, String assigneeName, LocalDateTime registerDate,
		DocStatusType status, String link, boolean isReviewed) {
		this.id = id;
		this.title = title;
		this.assigneeName = assigneeName;
		this.registerDate = registerDate;
		this.status = status;
		this.link = link;
		this.isReviewed = isReviewed;
	}

	public static WorkingDocumentWithReviewDto toDto(WorkingDocument workingDocument, boolean isReviewed) {
		return WorkingDocumentWithReviewDto.builder()
			.id(workingDocument.getId())
			.title(workingDocument.getTitle())
			.assigneeName(workingDocument.getWorking().getAssignee().getName())
			.registerDate(workingDocument.getRegisterDate())
			.status(workingDocument.getStatus())
			.link(workingDocument.getLink())
			.isReviewed(isReviewed)
			.build();
	}

	public static WorkingDocumentWithReviewDto toDto(WorkingDocument workingDocument) {
		return WorkingDocumentWithReviewDto.builder()
			.id(workingDocument.getId())
			.title(workingDocument.getTitle())
			.assigneeName(workingDocument.getWorking().getAssignee().getName())
			.registerDate(workingDocument.getRegisterDate())
			.status(workingDocument.getStatus())
			.link(workingDocument.getLink())
			.isReviewed(false)
			.build();
	}
}
