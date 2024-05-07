package com.docpoint.util;

import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

public class UserTestData {
	private static Long id = 1L;

	public static User createTeamMember(Team team) {
		return User.builder()
			.id(id++)
			.team(team)
			.name("팀 멤버")
			.email("email")
			.employeeNumber("1234")
			.role(RoleType.TEAM_MEMBER)
			.build();
	}

	public static User createPartLeader(Team team) {
		return User.builder()
			.id(id++)
			.team(team)
			.name("파트 리더")
			.email("email")
			.employeeNumber("1234")
			.role(RoleType.PART_LEADER)
			.build();
	}

	public static User createTeamLeader(Team team) {
		return User.builder()
			.id(id++)
			.team(team)
			.name("팀 리더")
			.email("email")
			.employeeNumber("1234")
			.role(RoleType.TEAM_LEADER)
			.build();
	}
}
