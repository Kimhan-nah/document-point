package com.docpoint.adapter.in.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class EmployeesResponseDto {
	private final List<UserDto> employees = new ArrayList<>();

	public EmployeesResponseDto(List<UserDto> employees) {
		this.employees.addAll(employees);
	}

	public static EmployeesResponseDto from(List<UserDto> employees) {
		return new EmployeesResponseDto(employees);
	}
}
