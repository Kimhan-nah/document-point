package com.docpoint.workingdoc.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.docpoint.working.domain.Working;
import com.docpoint.workingdoc.domain.type.DocStatusType;
import com.docpoint.workingdoc.domain.type.DocType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class WorkingDoc {
	@NonNull
	private final Working working;

	@NonNull
	private final String title;

	@NonNull
	private final String content;

	@NonNull
	private final DocStatusType status;

	private final boolean isDeleted;

	private final Map<DocType, List<String>> links = new HashMap<>();

	public WorkingDoc(@NonNull Working working, @NonNull String title, @NonNull String content,
		@NonNull DocStatusType status, boolean isDeleted) {
		this.working = working;
		this.title = title;
		this.content = content;
		this.status = status;
		this.isDeleted = isDeleted;
	}
}
