package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity.*;
import static com.docpoint.infrastructure.entity.QTeamJpaEntity.*;
import static com.docpoint.infrastructure.entity.QUserJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingJpaEntity.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity;
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

	/**
	 * 내 문서 목록 조회 (working_assignee.assignee_id = userId)
	 * @param userId
	 * @param pageable
	 * @param status
	 * @return
	 */
	@Override
	public Page<WorkingDocumentJpaEntity> findByAssigneeId(long userId, Pageable pageable, DocStatusType status) {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QWorkingAssigneeJpaEntity workingAssignee = workingAssigneeJpaEntity;
		QWorkingJpaEntity working = workingJpaEntity;

		List<WorkingDocumentJpaEntity> content = jpaQueryFactory
			.select(workingDocument)
			.from(working)
			.innerJoin(workingAssignee)
			.on(working.id.eq(workingAssignee.working.id).and(workingAssignee.assignee.id.eq(userId)))
			.innerJoin(workingDocument)
			.on(workingAssignee.working.id.eq(workingDocument.working.id))
			.where(eqStatus(status))
			.orderBy(workingDocument.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(workingDocument.count())
			.from(working)
			.innerJoin(workingAssignee)
			.on(working.id.eq(workingAssignee.working.id).and(workingAssignee.assignee.id.eq(userId)))
			.innerJoin(workingDocument)
			.on(workingAssignee.working.id.eq(workingDocument.working.id))
			.where(eqStatus(status));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	/**
	 * 요청 받은 문서 목록 조회 (document_reviewer.reivewer_id = reviewerId)
	 * @param reviewerId
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<WorkingDocumentJpaEntity> findByReviewerId(long reviewerId, Pageable pageable, DocStatusType status) {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QDocumentReviewerJpaEntity documentReviewer = documentReviewerJpaEntity;

		List<WorkingDocumentJpaEntity> content = jpaQueryFactory
			.select(workingDocument)
			.from(documentReviewer)
			.innerJoin(documentReviewer.workingDocument, workingDocument)
			.where(documentReviewer.reviewer.id.eq(reviewerId),
				eqStatus(status))
			.orderBy(workingDocument.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory.select(documentReviewer.count())
			.from(documentReviewer)
			.innerJoin(documentReviewer.workingDocument, workingDocument)
			.where(documentReviewer.reviewer.id.eq(reviewerId),
				eqStatus(status));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

	}

	@Override
	public Page<WorkingDocumentJpaEntity> findByUserIdExcludeStatus(long userId, Pageable pageable,
		DocStatusType excludeStatus, DocStatusType status) {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QDocumentReviewerJpaEntity documentReviewer = documentReviewerJpaEntity;

		List<WorkingDocumentJpaEntity> fetch = jpaQueryFactory
			.select(workingDocument)
			.from(documentReviewer)
			.innerJoin(workingDocument).on(documentReviewer.workingDocument.id.eq(workingDocument.id))
			.where(documentReviewer.reviewer.id.eq(userId),
				workingDocument.status.ne(excludeStatus),
				workingDocument.status.eq(status))
			.orderBy(workingDocument.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(workingDocument.count())
			.from(documentReviewer)
			.innerJoin(workingDocument).on(documentReviewer.workingDocument.id.eq(workingDocument.id))
			.where(documentReviewer.reviewer.id.eq(userId),
				workingDocument.status.ne(excludeStatus),
				workingDocument.status.eq(status));

		return PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne);
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
