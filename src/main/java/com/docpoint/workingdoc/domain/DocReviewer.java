package com.docpoint.workingdoc.domain;

import com.docpoint.user.domain.User;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class DocReviewer {
	@NonNull
	private final WorkingDoc workingDoc;

	@NonNull
	private final User user;

	public DocReviewer(@NonNull WorkingDoc workingDoc, @NonNull User user) {
		this.workingDoc = workingDoc;
		this.user = user;
	}
}
