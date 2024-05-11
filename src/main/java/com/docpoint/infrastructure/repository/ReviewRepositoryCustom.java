package com.docpoint.infrastructure.repository;

import java.util.List;

import com.docpoint.infrastructure.entity.ReviewJpaEntity;

public interface ReviewRepositoryCustom {
	List<ReviewJpaEntity> findAllByWorkingDocument(long workingDocumentId);

	boolean existsByWorkingDocumentAndReviewer(long workingDocumentId, long reviewerId);

	boolean existsByWorkingDocument(long workingDocumentId);
}
