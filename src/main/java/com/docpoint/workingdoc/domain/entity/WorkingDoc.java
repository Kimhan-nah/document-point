package com.docpoint.workingdoc.domain.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.docpoint.working.domain.entity.Working;
import com.docpoint.workingdoc.domain.type.DocStatusType;
import com.docpoint.workingdoc.domain.type.DocType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class WorkingDoc {
	private final Working working;

	private final String title;

	private final String content;

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
