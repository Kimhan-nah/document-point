package com.docpoint.adapter.in.dto;

import java.util.List;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class WorkingDocumentRequestDto {
	@NotBlank(message = "제목을 입력해주세요.")
	@Size(max = 255, message = "제목은 255자 이내로 입력해주세요.")
	private String title;

	@NotNull(message = "문서 종류를 선택해주세요. (Confluence, GitLab)")
	private DocType docType;

	@NotBlank(message = "링크를 입력해주세요.")
	@Size(max = 255, message = "링크는 255자 이내로 입력해주세요.")
	private String link;

	@NotNull(message = "리뷰어를 선택해주세요.")
	private List<Long> reviewerIds;

	@NotBlank(message = "내용을 입력해주세요.")
	@Size(max = 1000, message = "내용은 1000자 이내로 입력해주세요.")
	private String content;

	@NotNull(message = "작업 ID를 입력해주세요.")
	private Long workingId;

	public static WorkingDocument toWorkingDocumentEntity(WorkingDocumentRequestDto workingDocumentRequestDto) {
		return WorkingDocument.builder()
			.title(workingDocumentRequestDto.getTitle())
			.docType(workingDocumentRequestDto.getDocType())
			.link(workingDocumentRequestDto.getLink())
			.content(workingDocumentRequestDto.getContent())
			.build();
	}

	public WorkingDocument toWorkingDocumentEntity() {
		return WorkingDocument.builder()
			.title(title)
			.docType(docType)
			.link(link)
			.content(content)
			.build();
	}
}
