package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadCpEvaluationPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;
import com.docpoint.util.CpEvaluationTestData;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("기여도(CpEvaluation) 조회 service 테스트")
class GetCpEvaluationServiceTest {
	@InjectMocks
	private GetCpEvaluationsService getFinalCpService;

	@Mock
	private LoadCpEvaluationPort loadCpEvaluationPort;

	@Test
	@DisplayName("담당자인 팀 멤버가 최종 기여도를 조회할 경우	")
	void 기여도_조회_성공() {
		// given
		User assignee = UserTestData.createTeamMember(mock(Team.class));
		User teamLeader = UserTestData.createTeamLeader(mock(Team.class));
		Working working = WorkingTestData.createWorkingWithAssignee(assignee);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
		DocumentReviewer documentReviewer = DocumentReviewer.builder()
			.workingDocument(workingDocument)
			.reviewer(teamLeader)
			.build();
		CpEvaluation cpEvaluation = CpEvaluationTestData.createCpEvaluation(documentReviewer);
		given(loadCpEvaluationPort.loadByWorkingDocument(workingDocument))
			.willReturn(List.of(cpEvaluation));

		// when
		Map<RoleType, CpEvaluation> cpEvaluations = getFinalCpService.getCpEvaluations(assignee, workingDocument);

		// then
		assertThat(cpEvaluations).isNotNull();
		verify(loadCpEvaluationPort, times(1)).loadByWorkingDocument(workingDocument);
		assertThat(cpEvaluations.get(RoleType.TEAM_LEADER)).isEqualTo(cpEvaluation);
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
				.isInstanceOf(ForbiddenException.class)
				.hasMessage(ErrorType.FORBIDDEN_ASSIGNEE.getMessage());
		}
	}

}
