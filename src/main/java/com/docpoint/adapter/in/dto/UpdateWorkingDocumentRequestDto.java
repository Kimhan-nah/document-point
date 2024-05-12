package com.docpoint.adapter.in.dto;

import java.util.Set;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateWorkingDocumentRequestDto {
	@NotBlank(message = "제목을 입력해주세요.")
	@Size(max = 255, message = "제목은 255자 이내로 입력해주세요.")
	private String title;

	@NotNull(message = "문서 종류를 선택해주세요. (Confluence, GitLab)")
	private DocType docType;

	@NotBlank(message = "링크를 입력해주세요.")
	@Size(max = 255, message = "링크는 255자 이내로 입력해주세요.")
	private String link;

	@NotNull(message = "리뷰어를 선택해주세요.")
	private Set<Long> reviewerIds;

	@NotBlank(message = "내용을 입력해주세요.")
	@Size(max = 1000, message = "내용은 1000자 이내로 입력해주세요.")
	private String content;

	public static WorkingDocument toWorkingDocumentEntity(long id,
		UpdateWorkingDocumentRequestDto workingDocumentRequestDto) {
		return WorkingDocument.builder()
			.id(id)
			.title(workingDocumentRequestDto.getTitle())
			.docType(workingDocumentRequestDto.getDocType())
			.link(workingDocumentRequestDto.getLink())
			.content(workingDocumentRequestDto.getContent())
			.status(DocStatusType.REVIEW)
			.build();
	}

	@AssertTrue(message = "리뷰어를 선택해주세요.")
	public boolean isReviewerIdsNotEmpty() {
		return reviewerIds != null && !reviewerIds.isEmpty();
	}
}
