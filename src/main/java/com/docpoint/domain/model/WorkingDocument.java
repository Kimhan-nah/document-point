package com.docpoint.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDocument {
	private final Working working;

	private final String title;

	private final String content;

	private final DocStatusType status;

	private final Map<DocType, List<String>> links;

	private final boolean isDeleted;

	@Builder
	public WorkingDocument(Working working, String title, String content, DocStatusType status,
		Map<DocType, List<String>> links, boolean isDeleted) {
		this.working = Objects.requireNonNull(working);
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.status = Objects.requireNonNull(status);
		this.links = Objects.requireNonNull(links);
		this.isDeleted = isDeleted;
	}
}
