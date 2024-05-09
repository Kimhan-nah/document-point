package com.docpoint.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

interface WorkingDocumentRepositoryCustom {
	Page<WorkingDocumentJpaEntity> findByUserId(long userId, Pageable pageable, DocStatusType status);

	Page<WorkingDocumentWithReviewDto> findByUserIdWithExcludeStatus(long userId, Pageable pageable,
		DocStatusType status);

	Page<WorkingDocumentWithReviewDto> findWithReviewByUserId(long userId, Pageable pageable);

	Page<WorkingDocumentJpaEntity> findByTeamId(long teamId, Pageable pageable);
}
