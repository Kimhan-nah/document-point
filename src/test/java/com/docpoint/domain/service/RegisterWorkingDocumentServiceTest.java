package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;
import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("문서(WorkingDocument) 등록 서비스")
class RegisterWorkingDocumentServiceTest {
	@InjectMocks
	private RegisterWorkingDocumentService registerWorkingDocumentService;

	@Mock
	private SaveWorkingDocumentPort saveWorkingDocumentPort;

	@Test
	@DisplayName("WorkingDocument 요청 성공")
	void workingDocument_등록_성공() {
		// given
		Working working = WorkingTestData.createWorkingWithStatus(WorkingStatusType.DONE);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);

		// when
		registerWorkingDocumentService.registerWorkingDocument(workingDocument);

		// then
		verify(saveWorkingDocumentPort, times(1)).saveWorkingDocument(workingDocument);
	}

	@Nested
	@DisplayName("WorkingDocument 요청이 불가능한 working인 경우")
	class InvalidWorking {
		@Test
		@DisplayName("working이 삭제된 상태이면, CustomRuntimeException이 발생한다.")
		void 삭제된_working_검증() {
			// given
			Working deletedWorking = WorkingTestData.createDeletedWorking();
			WorkingDocument invalidWorkingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(
				deletedWorking);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService.registerWorkingDocument(invalidWorkingDocument))
				.isInstanceOf(CustomRuntimeException.class);
		}

		@Test
		@DisplayName("working이 대기(WAITING) 상태이면, CustomRuntimeException이 발생한다.")
		void working_상태_검증() {
			// given
			WorkingDocument invalidWorkingDocument = WorkingDocumentTestData.createWorkingDocument();

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService.registerWorkingDocument(invalidWorkingDocument))
				.isInstanceOf(CustomRuntimeException.class);
		}

	}

	@Nested
	@DisplayName("WorkingDocument로 유효성 검사 실패로 요청이 불가능한 경우")
	class InitWorkingDocumentFail {
		private static Working working;

		@BeforeEach
		void setUp() {
			working = WorkingTestData.createWorkingWithStatus(WorkingStatusType.DONE);
		}

		@Test
		@DisplayName("links의 url의 갯수가 1개 미만이면, CustomRuntimeException이 발생한다.")
		void link_갯수_검증_테스트() {
			// given
			Map<DocType, List<String>> invalidLinks = WorkingDocumentTestData.createLinks(DocType.CONFLUENCE,
				List.of());
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithLinks(invalidLinks);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService.registerWorkingDocument(workingDocument))
				.isInstanceOf(CustomRuntimeException.class);
		}

		@Test
		@DisplayName("WorkingDocument의 상태가 검토중(REVIEW)가 아니면 CustomRuntimeException이 발생한다.")
		void 문서_상태_검증_테스트() {
			// given
			WorkingDocument invalidWorkingDocument = WorkingDocumentTestData.createWorkingDocumentWithStatus(
				DocStatusType.APPROVAL_REQUEST);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService.registerWorkingDocument(invalidWorkingDocument))
				.isInstanceOf(CustomRuntimeException.class);
		}
	}
}
