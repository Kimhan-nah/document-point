package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;

public interface LoadTeamEmployeesPort {
	List<User> loadTeamEmployees(Team team);

	List<User> loadTeamMembers(Team team);

	List<User> loadPartLeaders(Team team);

	List<User> loadTeamLeaders(Team team);
}
