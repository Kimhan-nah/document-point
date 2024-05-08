package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeDto {
	private final long id;
	private final String name;

	private final String employeeNumber;

	private final RoleType roleType;

	@Builder
	public EmployeeDto(long id, String name, String employeeNumber, RoleType roleType) {
		this.id = id;
		this.name = name;
		this.employeeNumber = employeeNumber;
		this.roleType = roleType;
	}

	public static EmployeeDto toDto(User employee) {
		return EmployeeDto.builder()
			.id(employee.getId())
			.name(employee.getName())
			.employeeNumber(employee.getEmployeeId())
			.roleType(employee.getRole())
			.build();
	}

}
