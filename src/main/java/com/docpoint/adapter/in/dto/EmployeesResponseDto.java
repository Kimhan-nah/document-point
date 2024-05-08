package com.docpoint.adapter.in.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class EmployeesResponseDto {
	private final List<EmployeeDto> employees = new ArrayList<>();

	public EmployeesResponseDto(List<EmployeeDto> employees) {
		this.employees.addAll(employees);
	}

	public static EmployeesResponseDto from(List<EmployeeDto> employees) {
		return new EmployeesResponseDto(employees);
	}
}
