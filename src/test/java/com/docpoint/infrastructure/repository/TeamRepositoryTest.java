package com.docpoint.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.docpoint.annotation.DataJpaUnitTest;
import com.docpoint.infrastructure.entity.TeamJpaEntity;

@DataJpaUnitTest
@DisplayName("TeamRepository 테스트")
class TeamRepositoryTest {

	@Autowired
	private TeamRepository teamRepository;

	@Test
	@DisplayName("팀 저장 테스트")
	void testTeamRepository() {
		// given
		TeamJpaEntity team = new TeamJpaEntity("team1", false);
		teamRepository.save(team);

		// when
		int numberOfTeams = teamRepository.findAll().size();

		// then
		assertEquals(1, numberOfTeams);
	}

	@Test
	@DisplayName("팀 조회 테스트")
	void testFindTeam() {
		// given
		TeamJpaEntity team = new TeamJpaEntity("team1", false);
		teamRepository.save(team);

		// when
		TeamJpaEntity foundTeam = teamRepository.findById(team.getId()).orElse(null);

		// then
		assertNotNull(foundTeam);
		assertEquals(team.getId(), foundTeam.getId());
		assertEquals(team.getName(), foundTeam.getName());
		assertEquals(team.getIsDeleted(), foundTeam.getIsDeleted());
	}
}
