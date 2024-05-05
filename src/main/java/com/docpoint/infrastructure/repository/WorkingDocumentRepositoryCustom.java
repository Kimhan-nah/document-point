package com.docpoint.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

public interface WorkingDocumentRepositoryCustom {
	public Page<WorkingDocumentJpaEntity> findByUserId(long userId, Pageable pageable);

	public Page<WorkingDocumentWithReviewDto> findWithReviewByUserId(long userId, Pageable pageable);
}
