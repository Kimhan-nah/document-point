package com.docpoint.user.application.port.out;

import java.util.List;

import com.docpoint.user.domain.entity.Team;
import com.docpoint.user.domain.entity.User;

public interface LoadTeamEmployeesPort {
	List<User> loadTeamEmployees(Team team);

	List<User> loadTeamMembers(Team team);

	List<User> loadPartLeaders(Team team);

	List<User> loadTeamLeaders(Team team);
}
