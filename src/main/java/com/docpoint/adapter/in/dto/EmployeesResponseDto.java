package com.docpoint.adapter.in.dto;

import java.util.ArrayList;
import java.util.List;

import com.docpoint.domain.model.User;

public class EmployeesResponseDto {
	private final List<EmployeeDto> employees = new ArrayList<>();

	public EmployeesResponseDto(List<EmployeeDto> employees) {
		this.employees.addAll(employees);
	}

	public static EmployeesResponseDto from(List<User> users) {
		List<EmployeeDto> employees = new ArrayList<>();
		for (User user : users) {
			EmployeeDto employeeDto = EmployeeDto.from(user);
			employees.add(employeeDto);
		}
		return new EmployeesResponseDto(employees);
	}
}
