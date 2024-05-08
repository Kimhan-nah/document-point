package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
	private final String name;
	private final String employeeId;
	private final Long teamId;
	private final String teamName;
	private final RoleType role;

	@Builder
	public UserResponseDto(String name, String employeeId, Long teamId, String teamName, RoleType role) {
		this.name = name;
		this.employeeId = employeeId;
		this.teamId = teamId;
		this.teamName = teamName;
		this.role = role;
	}

	public static UserResponseDto from(User user) {
		return UserResponseDto.builder()
			.name(user.getName())
			.employeeId(user.getEmployeeId())
			.teamId(user.getTeam().getId())
			.teamName(user.getTeam().getName())
			.role(user.getRole())
			.build();
	}
}
