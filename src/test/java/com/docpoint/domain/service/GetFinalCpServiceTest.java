package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("기여도(CpEvaluation) 조회 service 테스트")
class GetFinalCpServiceTest {
	@InjectMocks
	private GetCpEvaluationsService getFinalCpService;

	@Mock
	private LoadCpEvaluationsPort loadCpEvaluationsPort;

	@Test
	@DisplayName("기여도 조회 성공 - 1개의 CpEvaluation이 조회되는 경우, CpEvaluation 1개를 반환한다.")
	void 기여도_조회_성공() {
		// given
		User assignee = UserTestData.createTeamMember(mock(Team.class));
		Working working = WorkingTestData.createWorkingWithAssignee(assignee);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
		given(loadCpEvaluationsPort.loadByWorkingDocument(workingDocument))
			.willReturn(List.of(mock(CpEvaluation.class)));

		// when
		List<CpEvaluation> cpEvaluations = getFinalCpService.getCpEvaluations(assignee, workingDocument);

		// then
		assertThat(cpEvaluations).isNotNull();
		assertThat(cpEvaluations.size()).isEqualTo(1);
		verify(loadCpEvaluationsPort, times(1)).loadByWorkingDocument(workingDocument);

	}

	@Nested
	@DisplayName("조회 실패")
	class 조회_실패 {
		@Test
		@DisplayName("TEAM_MEMBER이고 working 담당자 본인이 아닌 경우, ForbiddenException 발생")
		void 본인이_아닌_경우() {
			// given
			User assignee = UserTestData.createTeamMember(mock(Team.class));
			User teamMember = UserTestData.createTeamMember(mock(Team.class));
			Working working = WorkingTestData.createWorkingWithAssignee(assignee);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);

			// when, then
			assertThatThrownBy(() -> getFinalCpService.getCpEvaluations(teamMember, workingDocument))
				.isInstanceOf(ForbiddenException.class);
		}
	}

}
