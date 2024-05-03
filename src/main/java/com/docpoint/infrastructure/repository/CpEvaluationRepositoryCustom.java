package com.docpoint.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;

public interface CpEvaluationRepositoryCustom {
	public List<CpEvaluationJpaEntity> findByWorkingDocumentId(Long workingDocumentId);

	public Optional<CpEvaluationJpaEntity> findByWorkingDocumentIdAndDocumentReviewerId(
		Long workingDocumentId, Long documentReviewerId);
}
