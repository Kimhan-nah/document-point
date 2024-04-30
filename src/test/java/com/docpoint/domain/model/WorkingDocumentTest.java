package com.docpoint.domain.model;

import static com.docpoint.domain.type.DocStatusType.*;
import static com.docpoint.domain.type.DocType.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

@DisplayName("WorkingDocument 생성 테스트")
public class WorkingDocumentTest {
	@Test
	@DisplayName("WorkingDocument 생성 성공")
	void 생성_성공() {
		// when, then
		WorkingDocumentTestData.createWorkingDocument();
	}

	@Nested
	@DisplayName("생성 실패하는 경우")
	class InitWorkingDocFail {
		private static Working working;

		@BeforeEach
		void setUp() {
			working = WorkingTestData.createWorking();
		}

		@Test
		@DisplayName("title은 null이면 NullPointerException이 발생한다.")
		void titleNotNullTest() {
			// when, then
			assertThatThrownBy(
				() -> new WorkingDocument(1L, working, null, "content", REVIEW, GITLAB, "gitlab.com", false))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("content는 null이면 NullPointerException이 발생한다.")
		void contentNotNullTest() {
			// when, then
			assertThatThrownBy(
				() -> new WorkingDocument(1L, working, "title", null, REVIEW, GITLAB, "gitlab.com", false))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("link는 null이면 NullPointerException이 발생한다.")
		void linkNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDocument(1L, working, "title", "content", REVIEW, GITLAB, null, false))
				.isInstanceOf(NullPointerException.class);
		}
	}
}
