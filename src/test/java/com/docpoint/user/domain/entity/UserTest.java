package com.docpoint.user.domain.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.docpoint.user.domain.type.RoleType;

class UserTest {
	@Test
	@DisplayName("team이 null이면 NullPointerException이 발생한다.")
	void employeeNumberNotNullTest() {
		// when, then
		assertThatThrownBy(() -> User.builder()
			.team(null)
			.employeeNumber(1234)
			.name("name")
			.email("email")
			.role(RoleType.TEAM_MEMBER)
			.build())
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("employeeNumber가 0보다 작거나 같으면 IllegalArgumentException이 발생한다.")
	void employeeNumberGreaterThanZeroTest() {
		// when, then
		assertThatThrownBy(() -> User.builder()
			.team(new Team("team"))
			.employeeNumber(0)
			.name("name")
			.email("email")
			.role(RoleType.TEAM_MEMBER)
			.build())
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("name이 null이면 NullPointerException이 발생한다.")
	void nameNotNullTest() {
		// when, then
		assertThatThrownBy(() -> User.builder()
			.team(new Team("team"))
			.employeeNumber(1234)
			.name(null)
			.email("email")
			.role(RoleType.TEAM_MEMBER)
			.build())
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("email이 null이면 NullPointerException이 발생한다.")
	void emailNotNullTest() {
		// when, then
		assertThatThrownBy(() -> User.builder()
			.team(new Team("team"))
			.employeeNumber(1234)
			.name("name")
			.email(null)
			.role(RoleType.TEAM_MEMBER)
			.build())
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("role이 null이면 NullPointerException이 발생한다.")
	void roleNotNullTest() {
		// when, then
		assertThatThrownBy(() -> User.builder()
			.team(new Team("team"))
			.employeeNumber(1234)
			.name("name")
			.email("email")
			.role(null)
			.build())
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("isDeleted는 false로 초기화 된다.")
	void isDeletedFalseTest() {
		// when
		User user = User.builder()
			.team(new Team("team"))
			.employeeNumber(1234)
			.name("name")
			.email("email")
			.role(RoleType.TEAM_MEMBER)
			.build();

		// then
		assertThat(user.isDeleted()).isFalse();
	}
}
