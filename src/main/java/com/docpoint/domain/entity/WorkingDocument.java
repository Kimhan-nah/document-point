package com.docpoint.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDocument {
	private final Long id;
	private final LocalDateTime registerDate;
	private Working working;
	private String title;
	private String content;
	private DocType docType;
	private String link;
	private DocStatusType status;
	private boolean isDeleted;

	@Builder
	public WorkingDocument(Long id, Working working, String title, String content, DocStatusType status,
		DocType docType, String link, boolean isDeleted, LocalDateTime registerDate) {
		this.id = id;
		this.working = working;
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.status = Objects.requireNonNull(status);
		this.docType = Objects.requireNonNull(docType);
		this.link = Objects.requireNonNull(link);
		this.isDeleted = isDeleted;
		this.registerDate = registerDate;
	}

	public boolean isWorkingEmpty() {
		return this.working == null;
	}

	public void updateWorking(Working working) {
		this.working = working;
	}

	public void updateStatus(DocStatusType status) {
		this.status = status;
	}

	public void delete() {
		this.isDeleted = true;
	}
}
