package com.docpoint.infrastructure.repository;

import java.util.List;

import com.docpoint.infrastructure.entity.QWorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkingRepositoryCustomImpl implements WorkingRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<WorkingJpaEntity> searchUserWorking(long userId, String search) {
		QWorkingJpaEntity working = QWorkingJpaEntity.workingJpaEntity;
		QWorkingAssigneeJpaEntity workingAssignee = QWorkingAssigneeJpaEntity.workingAssigneeJpaEntity;

		return jpaQueryFactory.select(working)
			.from(working)
			.innerJoin(workingAssignee)
			.on(workingAssignee.assignee.id.eq(userId)
				.and(workingAssignee.working.id.eq(working.id)))
			.where(working.title.contains(search))
			.fetch();
	}
}
