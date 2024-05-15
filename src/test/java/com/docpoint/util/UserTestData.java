package com.docpoint.util;

import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;

public class UserTestData {
	private static Long id = 1L;

	public static User createTeamMember(Team team) {
		return User.builder()
			.id(id++)
			.team(team)
			.name("팀 멤버")
			.email("email")
			.employeeId("1234")
			.role(RoleType.TEAM_MEMBER)
			.build();
	}

	public static User createPartLeader(Team team) {
		return User.builder()
			.id(id++)
			.team(team)
			.name("파트 리더")
			.email("email")
			.employeeId("1234")
			.role(RoleType.PART_LEADER)
			.build();
	}

	public static User createTeamLeader(Team team) {
		return User.builder()
			.id(id++)
			.team(team)
			.name("팀 리더")
			.email("email")
			.employeeId("1234")
			.role(RoleType.TEAM_LEADER)
			.build();
	}

	public static UserJpaEntity createUserJpaEntity(TeamJpaEntity team) {
		return UserJpaEntity.builder()
			.team(team)
			.name("팀 멤버")
			.email("email")
			.employeeId("1234")
			.role(RoleType.TEAM_MEMBER)
			.password("password")
			.isDeleted(false)
			.build();
	}

	public static TeamJpaEntity createTeam() {
		return TeamJpaEntity.builder()
			.name("Team Name")
			.isDeleted(false)
			.build();
	}
}
