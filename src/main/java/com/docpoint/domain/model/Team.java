package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class Team {
	private final String name;

	private final boolean isDeleted;

	public Team(String name, boolean isDeleted) {
		this.name = Objects.requireNonNull(name);
		this.isDeleted = isDeleted;
	}
}
