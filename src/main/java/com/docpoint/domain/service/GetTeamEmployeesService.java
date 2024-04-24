package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetTeamEmployeesUseCase;
import com.docpoint.application.port.out.LoadTeamEmployeesPort;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTeamEmployeesService implements GetTeamEmployeesUseCase {
	private final LoadTeamEmployeesPort loadTeamEmployeesPort;

	@Override
	@Transactional(readOnly = true)
	public List<User> getEmployeesByTeam(Team team) {
		return loadTeamEmployeesPort.loadTeamEmployees(team);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getTeamMembersByTeam(Team team) {
		return loadTeamEmployeesPort.loadTeamMembers(team);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getPartLeadersByTeam(Team team) {
		return loadTeamEmployeesPort.loadPartLeaders(team);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getTeamLeadersByTeam(Team team) {
		return loadTeamEmployeesPort.loadTeamLeaders(team);
	}
}
