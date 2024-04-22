package com.docpoint.working.domain.entity;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.docpoint.user.domain.entity.User;
import com.docpoint.working.domain.type.WorkingCategoryType;
import com.docpoint.working.domain.type.WorkingStatusType;

class WorkingTest {
	@Test
	@DisplayName("writer가 null이면 NullPointerException이 발생한다.")
	void writerNotNullTest() {
		// when, then
		assertThatThrownBy(
			() -> Working.builder()
				.writer(null)
				.cp(1)
				.title("title")
				.content("content")
				.category(WorkingCategoryType.DEV)
				.dueDate(LocalDateTime.now())
				.recruitDate(LocalDateTime.now())
				.build()
		).isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("title이 null이면 NullPointerException이 발생한다.")
	void titleNotNullTest() {
		// when, then
		assertThatThrownBy(
			() -> Working.builder()
				.writer(mock(User.class))
				.cp(1)
				.title(null)
				.content("content")
				.category(WorkingCategoryType.DEV)
				.dueDate(LocalDateTime.now())
				.recruitDate(LocalDateTime.now())
				.build()
		).isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("content가 null이면 NullPointerException이 발생한다.")
	void contentNotNullTest() {
		// when, then
		assertThatThrownBy(
			() -> Working.builder()
				.writer(mock(User.class))
				.cp(1)
				.title("title")
				.content(null)
				.category(WorkingCategoryType.DEV)
				.dueDate(LocalDateTime.now())
				.recruitDate(LocalDateTime.now())
				.build()
		).isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("status를 완료(DONE)로 변경한다.")
	void changeStatusDone() {
		// given
		Working working = Working.builder()
			.writer(mock(User.class))
			.cp(1)
			.title("title")
			.content("content")
			.category(WorkingCategoryType.DEV)
			.dueDate(mock(LocalDateTime.class))
			.recruitDate(mock(LocalDateTime.class))
			.build();

		// when
		working = working.updateStatus(WorkingStatusType.DONE);

		// then
		assertThat(working.getStatus()).isEqualTo(WorkingStatusType.DONE);
	}

	@Nested
	@DisplayName("삭제된 경우(isDeleted가 true인 경우)")
	class Deleted {
		@Test
		@DisplayName("상태 변경시 IllegalArgumentException이 발생한다.")
		void changeStatusTest() {
			// given
			Working working = Working.builder()
				.writer(mock(User.class))
				.cp(1)
				.title("title")
				.content("content")
				.category(WorkingCategoryType.DEV)
				.dueDate(mock(LocalDateTime.class))
				.recruitDate(mock(LocalDateTime.class))
				.build();
			working = working.delete();

			// when, then
			Working finalWorking = working;
			assertThatThrownBy(() -> finalWorking.updateStatus(WorkingStatusType.DONE))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@DisplayName("생성자 초기화 상태 테스트")
	class InitValue {
		@Test
		@DisplayName("isDeleted는 false로 초기화 된다.")
		void isDeletedFalseTest() {
			// when
			Working working = Working.builder()
				.writer(mock(User.class))
				.cp(1)
				.title("title")
				.content("content")
				.category(WorkingCategoryType.DEV)
				.dueDate(mock(LocalDateTime.class))
				.recruitDate(mock(LocalDateTime.class))
				.build();

			// then
			assertThat(working.isDeleted()).isFalse();
		}

		@Test
		@DisplayName("status는 WAITING으로 초기화 된다.")
		void statusWaitingTest() {
			// when
			Working working = Working.builder()
				.writer(mock(User.class))
				.cp(1)
				.title("title")
				.content("content")
				.category(WorkingCategoryType.DEV)
				.dueDate(mock(LocalDateTime.class))
				.recruitDate(mock(LocalDateTime.class))
				.build();

			// then
			assertThat(working.getStatus()).isEqualTo(WorkingStatusType.WAITING);
		}

		@Test
		@DisplayName("assignee는 null로 초기화 된다.")
		void assigneeNullTest() {
			// when
			Working working = Working.builder()
				.writer(mock(User.class))
				.cp(1)
				.title("title")
				.content("content")
				.category(WorkingCategoryType.DEV)
				.dueDate(mock(LocalDateTime.class))
				.recruitDate(mock(LocalDateTime.class))
				.build();

			// then
			assertThat(working.getAssignee()).isNull();
		}

	}
}
