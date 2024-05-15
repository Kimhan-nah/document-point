package com.docpoint.util;

import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

public class DocumentReviewerTestData {
	public static DocumentReviewerJpaEntity createDocumentReviewerJpaEntity(
		WorkingDocumentJpaEntity document, UserJpaEntity reviewer) {
		return DocumentReviewerJpaEntity.builder()
			.workingDocument(document)
			.reviewer(reviewer)
			.build();
	}
}
