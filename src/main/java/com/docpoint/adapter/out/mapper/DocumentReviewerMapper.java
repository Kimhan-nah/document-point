package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;

public class DocumentReviewerMapper {
	public static DocumentReviewer mapToDomainEntity(DocumentReviewerJpaEntity documentReviewer) {
		return DocumentReviewer.builder()
			.id(documentReviewer.getId())
			.workingDocument(documentReviewer.isWorkingDocumentEmpty() ? null :
				WorkingDocumentMapper.mapToDomainEntity(documentReviewer.getWorkingDocument()))
			.reviewer(documentReviewer.isReviewerEmpty() ? null :
				UserMapper.mapToDomainEntity(documentReviewer.getReviewer()))
			.build();
	}
}
