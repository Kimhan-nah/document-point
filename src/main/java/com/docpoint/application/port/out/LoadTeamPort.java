package com.docpoint.application.port.out;

import java.util.Optional;

import com.docpoint.domain.model.Team;

public interface LoadTeamPort {
	Optional<Team> loadById(long teamId);
}
