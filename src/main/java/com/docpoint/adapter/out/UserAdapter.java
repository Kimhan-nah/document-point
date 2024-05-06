package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.adapter.out.mapper.UserMapper;
import com.docpoint.application.port.out.LoadEmployeePort;
import com.docpoint.application.port.out.LoadUserPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;
import com.docpoint.infrastructure.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserAdapter implements LoadEmployeePort, LoadUserPort {
	private final UserRepository userRepository;

	@Override
	public List<User> loadByTeam(Team team) {
		return userRepository.findByTeam_Id(team.getId())
			.stream()
			.map(UserMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public List<User> loadByTeamAndRole(Team team, RoleType role) {
		return userRepository.findByTeam_IdAndRole(team.getId(), role)
			.stream()
			.map(UserMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public Optional<User> loadById(long userId) {
		return userRepository.findFirstById(userId)
			.map(UserMapper::mapToDomainEntity);
	}

	@Override
	public Optional<User> loadTeamLeaderByTeam(Team team) {
		return userRepository.findFirstByTeam_IdAndRole(team.getId(), RoleType.TEAM_LEADER)
			.map(UserMapper::mapToDomainEntity);
	}
}
