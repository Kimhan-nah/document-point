package com.docpoint.workingdoc.domain.entity;

import com.docpoint.user.domain.entity.User;

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
