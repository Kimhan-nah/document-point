package com.docpoint.user.domain;

import com.docpoint.user.domain.type.RoleType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class User {
	@NonNull
	private final Team team;

	private final int employeeNumber;

	@NonNull
	private final String name;

	@NonNull
	private final String email;

	private final boolean isDeleted;

	@NonNull
	private final RoleType role;

	public User(@NonNull Team team, int employeeNumber, @NonNull String name, @NonNull String email, boolean isDeleted,
		@NonNull RoleType role) {
		this.team = team;
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.email = email;
		this.isDeleted = isDeleted;
		this.role = role;
	}
}
