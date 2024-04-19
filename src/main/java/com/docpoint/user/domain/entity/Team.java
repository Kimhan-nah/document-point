package com.docpoint.user.domain.entity;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Team {
	private final String name;

	private final boolean isDeleted;

	protected Team(@NonNull String name, boolean isDeleted) {
		this.name = name;
		this.isDeleted = isDeleted;
	}

	public Team(@NonNull String name) {
		this(name, false);
	}

	public Team delete() {
		return new Team(this.name, true);
	}
}
