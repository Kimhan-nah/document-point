package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;

public interface GetTeamEmployeesUseCase {
	/**
	 * 팀의 모든 직원 조회
	 */
	List<User> getEmployeesByTeam(Team team);

	/**
	 * 팀의 모든 팀원(TEAM_MEMBER) 조회
	 */
	List<User> getTeamMembersByTeam(Team team);

	/**
	 * 팀의 모든 파트장(PART_LEADER) 조회
	 */
	List<User> getPartLeadersByTeam(Team team);

	/**
	 * 팀의 모든 팀장(TEAM_LEADER) 조회
	 */
	List<User> getTeamLeadersByTeam(Team team);
}
