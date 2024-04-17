package com.docpoint.working.domain;

import java.time.LocalDateTime;

import com.docpoint.user.domain.User;
import com.docpoint.working.domain.type.WorkingCategoryType;
import com.docpoint.working.domain.type.WorkingStatusType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Working {
	@NonNull
	private final User writer;

	@NonNull
	private final User assignee;

	private final int cp;

	@NonNull
	private final String title;

	@NonNull
	private final String content;

	private final boolean isDeleted;

	@NonNull
	private final WorkingStatusType status;

	@NonNull
	private final WorkingCategoryType category;

	@NonNull
	private final LocalDateTime dueDate;

	@NonNull
	private final LocalDateTime recruitDate;

	public Working(@NonNull User writer, @NonNull User assignee, int cp, @NonNull String title, @NonNull String content,
		boolean isDeleted, @NonNull WorkingStatusType status, @NonNull WorkingCategoryType category,
		@NonNull LocalDateTime dueDate,
		@NonNull LocalDateTime recruitDate) {
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
