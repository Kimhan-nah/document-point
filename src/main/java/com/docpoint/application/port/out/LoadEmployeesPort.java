package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

public interface LoadEmployeesPort {
	List<User> loadByTeam(Team team);

	List<User> loadByTeamAndRole(Team team, RoleType role);
}
