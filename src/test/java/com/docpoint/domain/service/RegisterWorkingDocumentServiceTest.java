package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.util.UserTestData;
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
		registerWorkingDocumentService.registerWorkingDocument(workingDocument, working, working.getAssignee());

		// then
		verify(saveWorkingDocumentPort, times(1)).save(any(WorkingDocument.class));
	}

	@Nested
	@DisplayName("WorkingDocument 요청이 불가능한 working인 경우")
	class InvalidWorking {
		@Test
		@DisplayName("본인의 working이 아닌 경우, ForbiddenException이 발생한다.")
		void 본인_working_검증() {
			// given
			User assignee = UserTestData.createTeamMember(mock(Team.class));
			Working working = WorkingTestData.createWorkingWithAssignee(assignee);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
			User other = UserTestData.createTeamMember(mock(Team.class));

			// when, then
			assertThatThrownBy(
				() -> registerWorkingDocumentService.registerWorkingDocument(workingDocument, working, other))
				.isInstanceOf(ForbiddenException.class);
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
		@DisplayName("WorkingDocument의 상태가 검토중(REVIEW)가 아니면 BadRequestException이 발생한다.")
		void 문서_상태_검증_테스트() {
			// given
			WorkingDocument invalidWorkingDocument = WorkingDocumentTestData.createWorkingDocumentWithStatus(
				DocStatusType.APPROVAL_REQUEST);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService
				.registerWorkingDocument(invalidWorkingDocument, working, working.getAssignee()))
				.isInstanceOf(BadRequestException.class);
		}
	}
}
