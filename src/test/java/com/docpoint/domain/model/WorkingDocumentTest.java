package com.docpoint.domain.model;

import static com.docpoint.domain.type.DocStatusType.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.docpoint.domain.type.DocType;
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
		private static Map<DocType, List<String>> links;
		private static Working working;

		@BeforeEach
		void setUp() {
			links = WorkingDocumentTestData.createLinks();
			working = WorkingTestData.createWorking();
		}

		@Test
		@DisplayName("working이 null이면 NullPointerException이 발생한다.")
		void workingNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDocument(null, "title", "content", REVIEW, links, false))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("title은 null이면 NullPointerException이 발생한다.")
		void titleNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDocument(working, null, "content", REVIEW, links, false))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("content는 null이면 NullPointerException이 발생한다.")
		void contentNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDocument(working, "title", null, REVIEW, links, false))
				.isInstanceOf(NullPointerException.class);
		}

		@Test
		@DisplayName("links는 null이면 NullPointerException이 발생한다.")
		void linksNotNullTest() {
			// when, then
			assertThatThrownBy(() -> new WorkingDocument(working, "title", "content", REVIEW, null, false))
				.isInstanceOf(NullPointerException.class);
		}
	}
}
