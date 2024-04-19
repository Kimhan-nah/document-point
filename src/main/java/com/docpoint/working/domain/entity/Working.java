package com.docpoint.working.domain.entity;

import java.time.LocalDateTime;

import com.docpoint.user.domain.entity.User;
import com.docpoint.working.domain.type.WorkingCategoryType;
import com.docpoint.working.domain.type.WorkingStatusType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Working {
	private final User writer;

	private final User assignee;

	private final int cp;

	private final String title;

	private final String content;

	private final boolean isDeleted;

	private final WorkingStatusType status;

	private final WorkingCategoryType category;

	private final LocalDateTime dueDate;

	private final LocalDateTime recruitDate;

	public Working(@NonNull User writer, @NonNull User assignee, int cp, @NonNull String title, @NonNull String content,
		boolean isDeleted, @NonNull WorkingStatusType status, @NonNull WorkingCategoryType category,
		@NonNull LocalDateTime dueDate, @NonNull LocalDateTime recruitDate) {
		this.writer = writer;
		this.assignee = assignee;
		this.cp = cp;
		this.title = title;
		this.content = content;
		this.isDeleted = isDeleted;
		this.status = status;
		this.category = category;
		this.dueDate = dueDate;
		this.recruitDate = recruitDate;
	}
}
