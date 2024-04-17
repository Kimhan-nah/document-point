package com.docpoint.workingdoc.domain;

import com.docpoint.workingdoc.domain.type.RoleType;

import lombok.Getter;

@Getter
public class User {
	private final Team team;
	private final int employeeNumber;
	private final String name;
	private final String email;
	private final boolean isDeleted;
	private final RoleType role;

	public User(Team team, int employeeNumber, String name, String email, RoleType role) {
		this.team = team;
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.email = email;
		this.isDeleted = false;
		this.role = role;
	}
}
