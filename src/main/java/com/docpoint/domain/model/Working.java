package com.docpoint.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.docpoint.domain.type.WorkingCategoryType;
import com.docpoint.domain.type.WorkingStatusType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Working {
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
	public Working(User writer, User assignee, String title, String content, WorkingStatusType status,
		WorkingCategoryType category, LocalDateTime dueDate, LocalDateTime recruitDate, int cp, boolean isDeleted) {
		this.writer = Objects.requireNonNull(writer);
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
			.writer(writer)
			.assignee(assignee)
			.title(title)
			.content(content)
			.status(status)
			.category(category)
			.dueDate(dueDate)
			.recruitDate(recruitDate)
			.cp(cp)
			.isDeleted(isDeleted)
			.build();
	}
}
