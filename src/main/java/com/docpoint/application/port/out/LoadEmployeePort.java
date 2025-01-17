package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

public interface LoadEmployeePort {
	List<User> loadByTeam(Team team);

	List<User> loadByTeamAndRole(Team team, RoleType role);

	Optional<User> loadTeamLeaderByTeam(Team team);
}
