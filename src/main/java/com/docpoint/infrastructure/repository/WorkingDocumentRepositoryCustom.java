package com.docpoint.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

interface WorkingDocumentRepositoryCustom {
	/**
	 * 팀 멤버의 내 문서 목록 조회
	 */
	Page<WorkingDocumentJpaEntity> findByAssigneeId(long assigneeId, Pageable pageable, DocStatusType status);

	/**
	 * 팀 리더의 '받은 요청' 목록 조회
	 */
	Page<WorkingDocumentJpaEntity> findByUserIdExcludeStatus(long userId, Pageable pageable,
		DocStatusType excludeStatus, DocStatusType status);

	/**
	 * '받은 요청' 목록 조회
	 */
	Page<WorkingDocumentJpaEntity> findByReviewerId(long reviewerId, Pageable pageable, DocStatusType status);

	/**
	 * 팀 리더와 파트 리더의 '팀 전체 문서' 목록 조회
	 */
	Page<WorkingDocumentJpaEntity> findByTeamId(long teamId, Pageable pageable, DocStatusType status);
}
