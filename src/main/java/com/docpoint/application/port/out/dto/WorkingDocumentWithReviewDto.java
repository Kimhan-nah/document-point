package com.docpoint.application.port.out.dto;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;
import com.querydsl.core.annotations.QueryProjection;

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
	private Long reviewId;

	@QueryProjection
	public WorkingDocumentWithReviewDto(Long id, String title, String content, DocStatusType status, DocType docType,
		String link, boolean isDeleted, Long reviewId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.docType = docType;
		this.link = link;
		this.isDeleted = isDeleted;
		this.reviewId = reviewId;
	}
}
