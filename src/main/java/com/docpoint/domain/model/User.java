package com.docpoint.domain.model;

import java.util.Objects;

import com.docpoint.domain.type.RoleType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
	private final Team team;

	private final String name;

	private final String email;

	private final RoleType role;

	private final int employeeNumber;

	private final boolean isDeleted;

	@Builder
	public User(Team team, String name, String email, RoleType role, int employeeNumber, boolean isDeleted) {
		this.team = Objects.requireNonNull(team);
		this.name = Objects.requireNonNull(name);
		this.email = Objects.requireNonNull(email);
		this.role = Objects.requireNonNull(role);
		this.employeeNumber = employeeNumber;
		this.isDeleted = isDeleted;
	}
}
