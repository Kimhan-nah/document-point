package com.docpoint.domain.entity;

import java.util.Objects;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"id", "name"})
public class Team {
	private final Long id;

	private final String name;

	private boolean isDeleted;

	@Builder
	public Team(Long id, String name, boolean isDeleted) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.isDeleted = isDeleted;
	}

	public void delete() {
		this.isDeleted = true;
	}
}
