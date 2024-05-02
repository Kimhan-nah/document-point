package com.docpoint.application.port.in;

import com.docpoint.domain.entity.Team;

public interface GetTeamUseCase {
	Team getTeam(Long id);
}
