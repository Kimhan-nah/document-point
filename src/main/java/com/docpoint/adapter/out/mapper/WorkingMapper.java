package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.Working;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;

public class WorkingMapper {
	public static Working mapToDomainEntity(
		WorkingJpaEntity workingJpaEntity,
		WorkingAssigneeJpaEntity workingAssigneeJpaEntity) {
		return Working.builder()
			.id(workingJpaEntity.getId())
			.writer(workingJpaEntity.isUserEmpty() ? null : UserMapper.mapToDomainEntity(workingJpaEntity.getWriter()))
			.assignee(workingAssigneeJpaEntity.isAssigneeEmpty() ? null :
				UserMapper.mapToDomainEntity(workingAssigneeJpaEntity.getAssignee()))
			.title(workingJpaEntity.getTitle())
			.content(workingJpaEntity.getContent())
			.status(workingJpaEntity.getStatus())
			.category(workingJpaEntity.getCategory())
			.dueDate(workingJpaEntity.getDueDate())
			.recruitDate(workingJpaEntity.getRecruitDate())
			.cp(workingJpaEntity.getCp())
			.isDeleted(workingJpaEntity.getIsDeleted())
			.build();
	}
}
