package com.docpoint.user.domain.entity;

import com.docpoint.user.domain.type.RoleType;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class User {
	private final Team team;

	private final int employeeNumber;

	private final String name;

	private final String email;

	private final boolean isDeleted;

	private final RoleType role;

	protected User(@NonNull Team team, int employeeNumber, @NonNull String name, @NonNull String email,
		boolean isDeleted, @NonNull RoleType role) {
		this.team = team;
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.email = email;
		this.isDeleted = isDeleted;
		this.role = role;
	}

	@Builder
	public User(@NonNull Team team, int employeeNumber, @NonNull String name, @NonNull String email,
		@NonNull RoleType role) {
		this(team, employeeNumber, name, email, false, role);
	}
}
