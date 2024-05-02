package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetTeamEmployeesUseCase;
import com.docpoint.application.port.out.LoadEmployeePort;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetTeamEmployeesService implements GetTeamEmployeesUseCase {
	private final LoadEmployeePort loadEmployeePort;

	@Override
	@Transactional(readOnly = true)
	public List<User> getEmployeesByTeam(Team team, RoleType role) {
		if (role == null) {
			return loadEmployeePort.loadByTeam(team);
		}
		return loadEmployeePort.loadByTeamAndRole(team, role);
	}
}
