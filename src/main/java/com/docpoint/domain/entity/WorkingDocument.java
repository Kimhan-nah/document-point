package com.docpoint.domain.entity;

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
	public WorkingDocument(Long id, Working working, String title, String content, DocStatusType status,
		DocType docType, String link, boolean isDeleted) {
		this.id = id;
		this.working = working;
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.status = Objects.requireNonNull(status);
		this.docType = Objects.requireNonNull(docType);
		this.link = Objects.requireNonNull(link);
		this.isDeleted = isDeleted;
	}

	public boolean isWorkingEmpty() {
		return this.working == null;
	}

	public WorkingDocument updateWorking(Working working) {
		return WorkingDocument.builder()
			.id(this.id)
			.working(working)
			.title(this.title)
			.content(this.content)
			.status(this.status)
			.docType(this.docType)
			.link(this.link)
			.isDeleted(this.isDeleted)
			.build();
	}
}
