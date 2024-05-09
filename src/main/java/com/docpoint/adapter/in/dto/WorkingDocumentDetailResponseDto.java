package com.docpoint.adapter.in.dto;

import java.util.List;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDocumentDetailResponseDto {
	private final Long id;
	private final WorkingDto working;
	private final String title;
	private final String assigneeName;
	private final List<UserDto> reviewers;
	private final DocType docType;
	private final String link;
	private final String content;
	private final DocStatusType status;

	@Builder
	public WorkingDocumentDetailResponseDto(Long id, WorkingDto working, String title, String assigneeName,
		List<UserDto> reviewers, DocType docType, String link, String content, DocStatusType status) {
		this.id = id;
		this.working = working;
		this.title = title;
		this.assigneeName = assigneeName;
		this.reviewers = reviewers;
		this.docType = docType;
		this.link = link;
		this.content = content;
		this.status = status;
	}

	/**
	 * 정적 팩터리 메서드
	 * @param workingDocument
	 * @param workingDto
	 * @param reviewers
	 * @return
	 */
	public static WorkingDocumentDetailResponseDto of(WorkingDocument workingDocument, WorkingDto workingDto,
		List<UserDto> reviewers) {
		return WorkingDocumentDetailResponseDto.builder()
			.id(workingDocument.getId())
			.working(workingDto)
			.title(workingDocument.getTitle())
			.assigneeName(workingDto.getRequesterName())
			.reviewers(reviewers)
			.docType(workingDocument.getDocType())
			.link(workingDocument.getLink())
			.content(workingDocument.getContent())
			.status(workingDocument.getStatus())
			.build();
	}
}
