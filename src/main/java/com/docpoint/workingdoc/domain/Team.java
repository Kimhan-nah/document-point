package com.docpoint.workingdoc.domain;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Team {
	@NonNull
	private String name;

	private boolean isDeleted;

	public Team(@NonNull String name, boolean isDeleted) {
		this.name = name;
		this.isDeleted = isDeleted;
	}
}
