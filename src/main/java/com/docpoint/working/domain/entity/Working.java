package com.docpoint.working.domain.entity;

import java.time.LocalDateTime;

import com.docpoint.user.domain.entity.User;
import com.docpoint.working.domain.type.WorkingCategoryType;
import com.docpoint.working.domain.type.WorkingStatusType;

import lombok.Builder;
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

	private Working(@NonNull User writer, User assignee, int cp, @NonNull String title, @NonNull String content,
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

	/**
	 * assignee만 null이 가능하며, 나머지는 null이 아닌 값이어야 한다.
	 * <p> assignee는 null로 초기화 된다. </p>
	 * <p> isDeleted는 false로 초기화 된다. </p>
	 * <p> status는 WAITING으로 초기화 된다. </p>
	 */
	@Builder
	public Working(@NonNull User writer, int cp, @NonNull String title, @NonNull String content,
		@NonNull WorkingCategoryType category, @NonNull LocalDateTime dueDate, @NonNull LocalDateTime recruitDate) {
		this(writer, null, cp, title, content, false, WorkingStatusType.WAITING, category, dueDate, recruitDate);
	}

	public Working updateStatus(WorkingStatusType status) {
		if (this.isDeleted) {
			throw new IllegalArgumentException("삭제된 업무는 상태를 변경할 수 없습니다.");
		}
		return new Working(this.writer, this.assignee, this.cp, this.title, this.content, this.isDeleted, status,
			this.category, this.dueDate, this.recruitDate);
	}

	public Working assign(User assignee) {
		if (this.isDeleted) {
			throw new IllegalArgumentException("삭제된 업무는 담당자를 변경할 수 없습니다.");
		}
		return new Working(this.writer, assignee, this.cp, this.title, this.content, this.isDeleted, this.status,
			this.category, this.dueDate, this.recruitDate);
	}

	public Working delete() {
		return new Working(this.writer, this.assignee, this.cp, this.title, this.content, true, this.status,
			this.category, this.dueDate, this.recruitDate);
	}
}
