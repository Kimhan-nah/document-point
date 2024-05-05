package com.docpoint.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.docpoint.domain.type.WorkingCategoryType;
import com.docpoint.domain.type.WorkingStatusType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Working {
	private final Long id;

	private final User writer;

	private final User assignee;

	private final String title;

	private final String content;

	private final WorkingStatusType status;

	private final WorkingCategoryType category;

	private final LocalDateTime dueDate;

	private final LocalDateTime recruitDate;

	private final int cp;

	private final boolean isDeleted;

	@Builder
	public Working(Long id, User writer, User assignee, String title, String content, WorkingStatusType status,
		WorkingCategoryType category, LocalDateTime dueDate, LocalDateTime recruitDate, int cp, boolean isDeleted) {
		this.id = id;
		this.writer = writer;
		this.assignee = assignee;
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.status = Objects.requireNonNull(status);
		this.category = Objects.requireNonNull(category);
		this.dueDate = Objects.requireNonNull(dueDate);
		this.recruitDate = Objects.requireNonNull(recruitDate);
		this.cp = cp;
		this.isDeleted = isDeleted;
	}

	public Working updateStatus(WorkingStatusType status) {
		return Working.builder()
			.id(this.id)
			.writer(this.writer)
			.assignee(this.assignee)
			.title(this.title)
			.content(this.content)
			.status(status)
			.category(this.category)
			.dueDate(this.dueDate)
			.recruitDate(this.recruitDate)
			.cp(this.cp)
			.isDeleted(this.isDeleted)
			.build();
	}

	public Working updateAssignee(User assignee) {
		return Working.builder()
			.id(this.id)
			.writer(this.writer)
			.assignee(assignee)
			.title(this.title)
			.content(this.content)
			.status(this.status)
			.category(this.category)
			.dueDate(this.dueDate)
			.recruitDate(this.recruitDate)
			.cp(this.cp)
			.isDeleted(this.isDeleted)
			.build();
	}

	public boolean isWriterEmpty() {
		return this.writer == null;
	}

	public boolean isAssigneeEmpty() {
		return this.assignee == null;
	}
}
