package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity.*;
import static com.docpoint.infrastructure.entity.QReviewJpaEntity.*;

import java.util.List;

import com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.QReviewJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity;
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
			.where(reviewJpaEntity.documentReviewer.workingDocument.id.eq(workingDocumentId),
				reviewJpaEntity.documentReviewer.workingDocument.isDeleted.isFalse())
			.fetch();
	}

	@Override
	public boolean existsByWorkingDocumentAndReviewer(long workingDocumentId, long reviewerId) {
		QDocumentReviewerJpaEntity documentReviewer = documentReviewerJpaEntity;
		QReviewJpaEntity review = reviewJpaEntity;

		// 서브쿼리 생성
		Long documentReviewerId = jpaQueryFactory.
			select(documentReviewer.id)
			.from(documentReviewer)
			.where(documentReviewer.workingDocument.id.eq(workingDocumentId),
				documentReviewer.workingDocument.isDeleted.isFalse(),
				documentReviewer.reviewer.id.eq(reviewerId))
			.fetchOne();

		// 메인 쿼리 작성
		return jpaQueryFactory
			.selectOne()
			.from(review)
			.where(review.documentReviewer.id.eq(documentReviewerId))
			.limit(1)
			.fetchFirst() != null;
	}

	@Override
	public boolean existsByWorkingDocument(long workingDocumentId) {
		QWorkingDocumentJpaEntity workingDocument = QWorkingDocumentJpaEntity.workingDocumentJpaEntity;
		QDocumentReviewerJpaEntity documentReviewer = documentReviewerJpaEntity;
		QReviewJpaEntity review = reviewJpaEntity;

		return jpaQueryFactory
			.selectOne()
			.from(workingDocument)
			.where(workingDocument.id.eq(workingDocumentId),
				workingDocument.isDeleted.isFalse())
			.limit(1)
			.fetchFirst() != null;
	}
}
