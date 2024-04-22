package com.docpoint.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.docpoint.util.WorkingTestData;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;
import com.docpoint.util.WorkingDocTestData;

class WorkingDocTest {
	@Nested
	@DisplayName("조건을 위반하여 생성 실패하는 경우")
	class InitWorkingDocFail {
		private static final Map<DocType, List<String>> LINKS = WorkingDocTestData.createLinks();
		private static final Working WORKING = WorkingTestData.createWorking();

		@Test
		@DisplayName("working이 null이면 NullPointerException이 발생한다.")
		void workingNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDoc(null, "title", "content", LINKS))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("working이 대기(WAITING) 상태이면 IllegalArgumentException이 발생한다.")
		void workingDocTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDoc(WORKING, "title", "content", LINKS))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("대기 상태인 업무만 문서를 생성할 수 있습니다.");
		}

		@Test
		@DisplayName("title은 null이면 NullPointerException이 발생한다.")
		void titleNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDoc(WORKING, null, "content", LINKS))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("content는 null이면 NullPointerException이 발생한다.")
		void contentNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDoc(WORKING, "title", null, LINKS))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("links는 null이면 NullPointerException이 발생한다.")
		void linksNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDoc(WORKING, "title", "content", null))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("links의 url의 갯수가 1개 미만이면 IllegalArgumentException이 발생한다.")
		void linksSizeTest() {
			// given
			Map<DocType, List<String>> invalidLinks = WorkingDocTestData.createLinks(DocType.CONFLUENCE, List.of());

			// when, then
			assertThatThrownBy(() -> new WorkingDoc(WORKING, "title", "content", invalidLinks))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@DisplayName("성공적으로 생성된 경우")
	class InitWorkingDoc {
		private static final WorkingDoc WORKING_DOC = WorkingDocTestData.createWorkingDoc();

		@Test
		@DisplayName("status는 REVIEW(검토중)로 초기화 된다.")
		void statusNotNullTest() {
			// then
			assertThat(WORKING_DOC.getStatus()).isEqualTo(DocStatusType.REVIEW);
		}

		@Test
		@DisplayName("isDeleted는 false로 초기화 된다.")
		void isDeletedFalseTest() {
			// then
			assertThat(WORKING_DOC.isDeleted()).isFalse();
		}
	}
}
