package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("최종 기여도 조회 service 테스트")
class GetFinalCpServiceTest {
	@InjectMocks
	private GetFinalCpService getFinalCpService;

	@Mock
	private LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	@Mock
	private LoadCpEvaluationsPort loadCpEvaluationsPort;

	@Test
	@DisplayName("최종 기여도 조회 성공 - isDeleted는 false이며, cpReviewer는 TEAM_LEADER이다.")
	void getFinalCp() {
		// given
		User assignee = UserTestData.createTeamMember(mock(Team.class));
		Working working = WorkingTestData.createWorkingWithAssignee(assignee);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
		given(loadWorkingDocumentsPort.loadById(anyLong())).willReturn(Optional.of(workingDocument));
		given(loadCpEvaluationsPort.loadFinalCpByWorkingDocumentId(anyLong()))
			.willReturn(Optional.of(mock(CpEvaluation.class)));

		// when
		CpEvaluation finalCp = getFinalCpService.getFinalCp(assignee, anyLong());

		// then
		assertThat(finalCp).isNotNull();
		verify(loadCpEvaluationsPort, times(1)).loadFinalCpByWorkingDocumentId(anyLong());

	}

	@Nested
	@DisplayName("조회 실패")
	class Leaders {
		@Test
		@DisplayName("working document가 존재하지 않는 경우, NotFoundException 발생")
		void WorkingDocument_존재하지_않은_경우() {
			// given
			given(loadWorkingDocumentsPort.loadById(anyLong())).willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(() -> getFinalCpService.getFinalCp(mock(User.class), anyLong()))
				.isInstanceOf(NotFoundException.class);
		}

		@Test
		@DisplayName("working document가 삭제된 경우, NotFoundException 발생")
		void WorkingDocument_삭제된_경우() {
			// given
			WorkingDocument workingDocument = WorkingDocumentTestData.createDeletedWorkingDocument();
			given(loadWorkingDocumentsPort.loadById(anyLong())).willReturn(Optional.of(workingDocument));

			// when, then
			assertThatThrownBy(() -> getFinalCpService.getFinalCp(mock(User.class), anyLong()))
				.isInstanceOf(NotFoundException.class);
		}

		@Test
		@DisplayName("TEAM_MEMBER이고 working 담당자 본인이 아닌 경우, ForbiddenException 발생")
		void 본인이_아닌_경우() {
			// given
			User assignee = UserTestData.createTeamMember(mock(Team.class));
			User teamMember = UserTestData.createTeamMember(mock(Team.class));
			Working working = WorkingTestData.createWorkingWithAssignee(assignee);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
			given(loadWorkingDocumentsPort.loadById(anyLong())).willReturn(Optional.of(workingDocument));

			// when, then
			assertThatThrownBy(() -> getFinalCpService.getFinalCp(teamMember, anyLong()))
				.isInstanceOf(ForbiddenException.class);
		}
	}

}
