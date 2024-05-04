package com.docpoint.infrastructure.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.infrastructure.entity.ReviewJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

public interface WorkingDocumentRepositoryCustom {
	public Page<WorkingDocumentJpaEntity> findByUserId(long userId, Pageable pageable);

	public Page<Map.Entry<WorkingDocumentJpaEntity, ReviewJpaEntity>> findWithReviewByUserId(long userId,
		Pageable pageable);
}
