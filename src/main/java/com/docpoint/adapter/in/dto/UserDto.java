package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
	private final long id;
	private final String name;

	private final String employeeId;

	private final RoleType role;

	@Builder
	public UserDto(long id, String name, String employeeId, RoleType role) {
		this.id = id;
		this.name = name;
		this.employeeId = employeeId;
		this.role = role;
	}

	public static UserDto toDto(User employee) {
		return UserDto.builder()
			.id(employee.getId())
			.name(employee.getName())
			.employeeId(employee.getEmployeeId())
			.role(employee.getRole())
			.build();
	}

}
