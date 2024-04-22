package com.docpoint.domain.model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class DocReviewer {
	private final WorkingDoc workingDoc;

	private final User user;

	public DocReviewer(@NonNull WorkingDoc workingDoc, @NonNull User user) {
		this.workingDoc = workingDoc;
		this.user = user;
	}
}
