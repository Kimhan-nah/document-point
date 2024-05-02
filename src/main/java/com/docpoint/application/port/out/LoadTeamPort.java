package com.docpoint.application.port.out;

import java.util.Optional;

import com.docpoint.domain.entity.Team;

public interface LoadTeamPort {
	Optional<Team> load(long teamId);
}
