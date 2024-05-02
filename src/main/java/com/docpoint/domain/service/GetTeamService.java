package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetTeamUseCase;
import com.docpoint.application.port.out.LoadTeamPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetTeamService implements GetTeamUseCase {
	private final LoadTeamPort loadTeamPort;

	@Override
	@Transactional(readOnly = true)
	public Team getTeam(Long id) {
		Team team = loadTeamPort.load(id).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_TEAM));
		if (team.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_TEAM);
		}
		return team;
	}
}
