package com.docpoint.domain.model;

import java.util.Objects;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDocument {
	private final Long id;

	private final Working working;

	private final String title;

	private final String content;

	private final DocStatusType status;

	private final DocType docType;

	private final String link;

	private final boolean isDeleted;

	@Builder
	public WorkingDocument(Working working, String title, String content, DocStatusType status, DocType docType,
		String link, boolean isDeleted) {
		this.id = null;
		this.working = Objects.requireNonNull(working);
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.status = Objects.requireNonNull(status);
		this.docType = Objects.requireNonNull(docType);
		this.link = Objects.requireNonNull(link);
		this.isDeleted = isDeleted;
	}
}
