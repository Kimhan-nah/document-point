package com.docpoint.domain.service;

import java.util.List;

import com.docpoint.application.port.in.GetTeamEmployeesUseCase;
import com.docpoint.application.port.out.LoadTeamEmployeesPort;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class GetTeamEmployeesService implements GetTeamEmployeesUseCase {
	private final LoadTeamEmployeesPort loadTeamEmployeesPort;

	@Override
	public List<User> getEmployeesByTeam(Team team) {
		return loadTeamEmployeesPort.loadTeamEmployees(team);
	}

	@Override
	public List<User> getTeamMembersByTeam(Team team) {
		return loadTeamEmployeesPort.loadTeamMembers(team);
	}

	@Override
	public List<User> getPartLeadersByTeam(Team team) {
		return loadTeamEmployeesPort.loadPartLeaders(team);
	}

	@Override
	public List<User> getTeamLeadersByTeam(Team team) {
		return loadTeamEmployeesPort.loadTeamLeaders(team);
	}
}
