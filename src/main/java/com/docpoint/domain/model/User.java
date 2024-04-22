package com.docpoint.domain.model;

import com.docpoint.domain.type.RoleType;

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

	private User(@NonNull Team team, int employeeNumber, @NonNull String name, @NonNull String email,
		boolean isDeleted, @NonNull RoleType role) {
		if (employeeNumber <= 0) {
			throw new IllegalArgumentException("employeeNumber must be greater than 0");
		}
		this.team = team;
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.email = email;
		this.isDeleted = isDeleted;
		this.role = role;
	}

	/**
	 * @throws IllegalArgumentException employeeNumber가 0보다 작거나 같은 경우
	 */
	@Builder
	public User(@NonNull Team team, int employeeNumber, @NonNull String name, @NonNull String email,
		@NonNull RoleType role) {
		this(team, employeeNumber, name, email, false, role);
	}
}
