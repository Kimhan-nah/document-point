package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.User;
import com.docpoint.infrastructure.entity.UserJpaEntity;

public class UserMapper {
	public static User mapToDomainEntity(UserJpaEntity user) {
		return User.builder()
			.id(user.getId())
			.name(user.getName())
			.email(user.getEmail())
			.role(user.getRole())
			.employeeNumber(user.getEmployeeNumber())
			.isDeleted(user.getIsDeleted())
			.team(user.isTeamEmpty() ? null : TeamMapper.mapToDomainEntity(user.getTeam()))
			.build();
	}
}
