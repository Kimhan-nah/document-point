package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QReviewJpaEntity.*;

import java.util.List;

import com.docpoint.infrastructure.entity.ReviewJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ReviewJpaEntity> findAllByWorkingDocument(long workingDocumentId) {
		return jpaQueryFactory
			.selectFrom(reviewJpaEntity)
			.where(reviewJpaEntity.documentReviewer.workingDocument.id.eq(workingDocumentId))
			.fetch();
	}
}
