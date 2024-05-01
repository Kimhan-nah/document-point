package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;
import com.docpoint.domain.type.RoleType;

public interface GetTeamEmployeesUseCase {
	/**
	 * 팀의 모든 직원 조회
	 */
	List<User> getEmployeesByTeam(Team team, RoleType role);
}
