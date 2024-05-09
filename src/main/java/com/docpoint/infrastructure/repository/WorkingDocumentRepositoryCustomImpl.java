package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity.*;
import static com.docpoint.infrastructure.entity.QReviewJpaEntity.*;
import static com.docpoint.infrastructure.entity.QTeamJpaEntity.*;
import static com.docpoint.infrastructure.entity.QUserJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingJpaEntity.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.docpoint.application.port.out.dto.QWorkingDocumentWithReviewDto;
import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.QReviewJpaEntity;
import com.docpoint.infrastructure.entity.QTeamJpaEntity;
import com.docpoint.infrastructure.entity.QUserJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkingDocumentRepositoryCustomImpl implements WorkingDocumentRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<WorkingDocumentJpaEntity> findByUserId(long userId, Pageable pageable, DocStatusType status) {
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
			.where(eqStatus(status))
			.orderBy(workingDocument.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(workingDocument.count())
			.from(working)
			.join(workingAssignee)
			.on(working.id.eq(workingAssignee.working.id).and(workingAssignee.assignee.id.eq(userId)))
			.join(workingDocument)
			.on(workingAssignee.working.id.eq(workingDocument.working.id))
			.where(eqStatus(status));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<WorkingDocumentWithReviewDto> findWithReviewByUserId(long userId, Pageable pageable) {
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

	@Override
	public Page<WorkingDocumentWithReviewDto> findByUserIdWithExcludeStatus(long userId, Pageable pageable,
		DocStatusType status) {
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
			.where(workingDocument.status.ne(status))
			.orderBy(workingDocument.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory.select(documentReviewer.count())
			.from(review)
			.rightJoin(review.documentReviewer, documentReviewer)
			.on(documentReviewer.reviewer.id.eq(userId))
			.join(documentReviewer.workingDocument, workingDocument)
			.where(workingDocument.status.ne(status));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<WorkingDocumentJpaEntity> findByTeamId(long teamId, Pageable pageable) {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QWorkingAssigneeJpaEntity workingAssignee = workingAssigneeJpaEntity;
		QWorkingJpaEntity working = workingJpaEntity;
		QUserJpaEntity user = userJpaEntity;
		QTeamJpaEntity team = teamJpaEntity;

		List<WorkingDocumentJpaEntity> content = jpaQueryFactory
			.select(workingDocument)
			.from(team)
			.innerJoin(user).on(user.team.id.eq(teamId))
			.innerJoin(workingAssignee).on(user.id.eq(workingAssignee.assignee.id))
			.innerJoin(working).on(workingAssignee.working.id.eq(working.id))
			.innerJoin(workingDocument).on(working.id.eq(workingDocument.working.id))
			.orderBy(workingDocument.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(workingDocument.count())
			.innerJoin(user).on(user.team.id.eq(teamId))
			.innerJoin(workingAssignee).on(user.id.eq(workingAssignee.assignee.id))
			.innerJoin(working).on(workingAssignee.working.id.eq(working.id))
			.innerJoin(workingDocument).on(working.id.eq(workingDocument.working.id));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	private BooleanExpression eqStatus(DocStatusType status) {
		return status != null ? workingDocumentJpaEntity.status.eq(status) : null;
	}
}
