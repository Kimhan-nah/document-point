package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingJpaEntity.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkingDocumentRepositoryCustomImpl implements WorkingDocumentRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<WorkingDocumentJpaEntity> findByUserId(long userId, Pageable pageable) {
		List<WorkingDocumentJpaEntity> content = jpaQueryFactory.selectFrom(workingDocumentJpaEntity)
			.join(workingDocumentJpaEntity.working, workingJpaEntity)
			.join(workingAssigneeJpaEntity.working, workingJpaEntity)
			.where(workingAssigneeJpaEntity.assignee.id.eq(userId))
			.orderBy(workingDocumentJpaEntity.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long totalCount = jpaQueryFactory.selectFrom(workingDocumentJpaEntity)
			.join(workingDocumentJpaEntity.working, workingJpaEntity)
			.join(workingAssigneeJpaEntity.working, workingJpaEntity)
			.where(workingAssigneeJpaEntity.assignee.id.eq(userId))
			.fetch().size();

		return new PageImpl<>(content, pageable, totalCount);
	}
}
