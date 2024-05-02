package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

public interface GetTeamEmployeesUseCase {
	/**
	 * 팀의 모든 직원 조회
	 */
	List<User> getEmployeesByTeam(Team team, RoleType role);
}
