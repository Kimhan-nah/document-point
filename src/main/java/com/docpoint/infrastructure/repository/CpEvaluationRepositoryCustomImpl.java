package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QCpEvaluationJpaEntity.*;

import java.util.List;
import java.util.Optional;

import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CpEvaluationRepositoryCustomImpl implements CpEvaluationRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<CpEvaluationJpaEntity> findByWorkingDocumentId(Long workingDocumentId) {
		return jpaQueryFactory.select(cpEvaluationJpaEntity)
			.from(cpEvaluationJpaEntity)
			.where(cpEvaluationJpaEntity.documentReviewer.workingDocument.id.eq(workingDocumentId))
			.fetch();
	}

	@Override
	public Optional<CpEvaluationJpaEntity> findByWorkingDocumentIdAndDocumentReviewerId(Long workingDocumentId,
		Long documentReviewerId) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(cpEvaluationJpaEntity)
			.where(cpEvaluationJpaEntity.documentReviewer.workingDocument.id.eq(workingDocumentId)
				.and(cpEvaluationJpaEntity.documentReviewer.id.eq(documentReviewerId)))
			.fetchFirst());
	}
}
