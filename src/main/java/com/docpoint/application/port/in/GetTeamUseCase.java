package com.docpoint.application.port.in;

import com.docpoint.domain.model.Team;

public interface GetTeamUseCase {
	Team getTeam(Long id);
}
