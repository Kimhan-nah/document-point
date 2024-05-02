package com.docpoint.domain.entity;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Team {
	private final Long id;

	private final String name;

	private final boolean isDeleted;

	@Builder
	public Team(Long id, String name, boolean isDeleted) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.isDeleted = isDeleted;
	}
}
