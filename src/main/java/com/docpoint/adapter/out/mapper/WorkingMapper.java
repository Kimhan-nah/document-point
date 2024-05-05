package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.Working;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;

public class WorkingMapper {
	public static Working mapToDomainEntityWithAssignee(
		WorkingJpaEntity working,
		WorkingAssigneeJpaEntity workingAssignee) {
		return Working.builder()
			.id(working.getId())
			.writer(working.isUserEmpty() ? null : UserMapper.mapToDomainEntity(working.getWriter()))
			.assignee(workingAssignee.isAssigneeEmpty() ? null :
				UserMapper.mapToDomainEntity(workingAssignee.getAssignee()))
			.title(working.getTitle())
			.content(working.getContent())
			.status(working.getStatus())
			.category(working.getCategory())
			.dueDate(working.getDueDate())
			.recruitDate(working.getRecruitDate())
			.cp(working.getCp())
			.isDeleted(working.getIsDeleted())
			.build();
	}

	public static Working mapToDomainEntity(WorkingJpaEntity working) {
		return Working.builder()
			.id(working.getId())
			.writer(working.isUserEmpty() ? null : UserMapper.mapToDomainEntity(working.getWriter()))
			.title(working.getTitle())
			.content(working.getContent())
			.status(working.getStatus())
			.category(working.getCategory())
			.dueDate(working.getDueDate())
			.recruitDate(working.getRecruitDate())
			.cp(working.getCp())
			.isDeleted(working.getIsDeleted())
			.build();
	}

	public static WorkingJpaEntity mapToJpaEntity(Working working) {
		return WorkingJpaEntity.builder()
			.writer(working.isWriterEmpty() ? null :
				UserMapper.mapToJpaEntity(working.getWriter()))
			.title(working.getTitle())
			.content(working.getContent())
			.status(working.getStatus())
			.category(working.getCategory())
			.dueDate(working.getDueDate())
			.recruitDate(working.getRecruitDate())
			.cp(working.getCp())
			.isDeleted(working.isDeleted())
			.build();
	}
}
