package com.docpoint.adapter.out;

import java.util.Optional;

import com.docpoint.adapter.out.mapper.TeamMapper;
import com.docpoint.application.port.out.LoadTeamPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeamAdapter implements LoadTeamPort {
	private final TeamRepository teamRepository;

	@Override
	public Optional<Team> load(long teamId) {
		TeamJpaEntity team = teamRepository.findById(teamId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_TEAM));
		return Optional.of(TeamMapper.mapToDomainEntity(team));
	}
}
