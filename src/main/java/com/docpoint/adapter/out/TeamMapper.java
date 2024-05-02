package com.docpoint.adapter.out;

import com.docpoint.domain.entity.Team;
import com.docpoint.infrastructure.entity.TeamJpaEntity;

public class TeamMapper {
	public static Team mapToDomainEntity(TeamJpaEntity team) {
		return Team.builder()
			.id(team.getId())
			.name(team.getName())
			.isDeleted(team.getIsDeleted())
			.build();
	}
}
