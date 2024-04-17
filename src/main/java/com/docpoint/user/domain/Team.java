package com.docpoint.user.domain;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Team {
	@NonNull
	private final String name;

	private final boolean isDeleted;

	public Team(@NonNull String name, boolean isDeleted) {
		this.name = name;
		this.isDeleted = isDeleted;
	}
}
