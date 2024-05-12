package com.docpoint.adapter.in.dto;

import java.time.LocalDateTime;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDocumentDto {
	private final Long id;
	private final String title;
	private final String assigneeName;
	private final LocalDateTime registerDate;
	private final DocStatusType status;

	@Builder
	public WorkingDocumentDto(Long id, String title, String assigneeName, LocalDateTime registerDate,
		DocStatusType status) {
		this.id = id;
		this.title = title;
		this.assigneeName = assigneeName;
		this.registerDate = registerDate;
		this.status = status;
	}

	public static WorkingDocumentDto toDto(WorkingDocument workingDocument) {
		return WorkingDocumentDto.builder()
			.id(workingDocument.getId())
			.title(workingDocument.getTitle())
			.assigneeName(workingDocument.getWorking().getAssignee().getName())
			.registerDate(workingDocument.getRegisterDate())
			.status(workingDocument.getStatus())
			.build();
	}
}
