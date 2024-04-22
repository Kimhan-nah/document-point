package com.docpoint.domain.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.docpoint.domain.type.WorkingCategoryType;
import com.docpoint.domain.type.WorkingStatusType;

@DisplayName("Working 생성 테스트")
public class WorkingTest {
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
			.status(WorkingStatusType.WAITING)
			.build();

		// when
		working = working.updateStatus(WorkingStatusType.DONE);

		// then
		assertThat(working.getStatus()).isEqualTo(WorkingStatusType.DONE);
	}
}
