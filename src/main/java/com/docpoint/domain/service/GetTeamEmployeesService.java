package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetTeamEmployeesUseCase;
import com.docpoint.application.port.out.LoadEmployeesPort;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetTeamEmployeesService implements GetTeamEmployeesUseCase {
	private final LoadEmployeesPort loadEmployeesPort;

	@Override
	@Transactional(readOnly = true)
	public List<User> getEmployeesByTeam(Team team, RoleType role) {
		if (role == null) {
			return loadEmployeesPort.loadByTeam(team);
		}
		return loadEmployeesPort.loadByTeamAndRole(team, role);
	}
}
