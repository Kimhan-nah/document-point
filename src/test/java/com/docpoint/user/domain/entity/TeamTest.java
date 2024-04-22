package com.docpoint.user.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {
	@Test
	@DisplayName("팀 삭제시 삭제된 팀의 isDeleted는 true")
	void delete() {
		// given
		Team team = new Team("team");

		// when
		team = team.delete();

		// then
		assertEquals(true, team.isDeleted());
	}

	@Test
	@DisplayName("이름(name)이 null이면 NullPointerException")
	void nameIsNull() {
		// when, then
		assertThrows(NullPointerException.class, () -> new Team(null));
	}
}
