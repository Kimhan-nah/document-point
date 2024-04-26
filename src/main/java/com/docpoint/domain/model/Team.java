package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class Team {
	private final Long id;

	private final String name;

	private final boolean isDeleted;

	public Team(String name, boolean isDeleted) {
		this.id = null;
		this.name = Objects.requireNonNull(name);
		this.isDeleted = isDeleted;
	}
}
