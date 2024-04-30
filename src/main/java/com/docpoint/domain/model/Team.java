package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class Team {
	private final Long id;

	private final String name;

	private final boolean isDeleted;

	public Team(Long id, String name, boolean isDeleted) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.isDeleted = isDeleted;
	}
}
