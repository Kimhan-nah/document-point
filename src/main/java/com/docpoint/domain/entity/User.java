package com.docpoint.domain.entity;

import java.util.Objects;

import com.docpoint.domain.type.RoleType;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"id", "employeeId", "name"})
public class User {
	private final Long id;

	private final Team team;

	private final String name;

	private final String email;

	private final RoleType role;

	private final String employeeId;

	private final boolean isDeleted;

	@Builder
	public User(Long id, Team team, String name, String email, RoleType role, String employeeId,
		boolean isDeleted) {
		this.id = id;
		this.team = team;
		this.name = Objects.requireNonNull(name);
		this.email = Objects.requireNonNull(email);
		this.role = Objects.requireNonNull(role);
		this.employeeId = Objects.requireNonNull(employeeId);
		this.isDeleted = isDeleted;
	}

	public boolean isTeamEmpty() {
		return this.team == null;
	}
}
