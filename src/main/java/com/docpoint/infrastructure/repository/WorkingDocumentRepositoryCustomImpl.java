package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity.*;
import static com.docpoint.infrastructure.entity.QReviewJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingJpaEntity.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.docpoint.application.port.out.dto.QWorkingDocumentWithReviewDto;
import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.QReviewJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkingDocumentRepositoryCustomImpl implements WorkingDocumentRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<WorkingDocumentJpaEntity> findByUserId(long userId, Pageable pageable) {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QWorkingAssigneeJpaEntity workingAssignee = workingAssigneeJpaEntity;
		QWorkingJpaEntity working = workingJpaEntity;

		List<WorkingDocumentJpaEntity> content = jpaQueryFactory
			.select(workingDocument)
			.from(working)
			.join(workingAssignee)
			.on(working.id.eq(workingAssignee.working.id).and(workingAssignee.assignee.id.eq(userId)))
			.join(workingDocument)
			.on(workingAssignee.working.id.eq(workingDocument.working.id))
			.orderBy(workingDocument.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(working.count())
			.from(working)
			.join(workingAssignee)
			.on(working.id.eq(workingAssignee.working.id).and(workingAssignee.assignee.id.eq(userId)))
			.join(workingDocument)
			.on(workingAssignee.working.id.eq(workingDocument.working.id));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<WorkingDocumentWithReviewDto> findWithReviewByUserId(long userId,
		Pageable pageable) {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QReviewJpaEntity review = reviewJpaEntity;
		QDocumentReviewerJpaEntity documentReviewer = documentReviewerJpaEntity;

		List<WorkingDocumentWithReviewDto> content = jpaQueryFactory
			.select(new QWorkingDocumentWithReviewDto(
				workingDocument.id,
				workingDocument.title,
				workingDocument.content,
				workingDocument.status,
				workingDocument.type,
				workingDocument.link,
				workingDocument.isDeleted,
				review.id))
			.from(review)
			.rightJoin(review.documentReviewer, documentReviewer)
			.on(documentReviewer.reviewer.id.eq(userId))
			.join(documentReviewer.workingDocument, workingDocument)
			.orderBy(workingDocument.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory.select(documentReviewer.count())
			.from(review)
			.rightJoin(review.documentReviewer, documentReviewer)
			.on(documentReviewer.reviewer.id.eq(userId))
			.join(documentReviewer.workingDocument, workingDocument);

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

	}
}
