package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadEmployeePort;
import com.docpoint.application.port.out.SaveDocumentReviewerPort;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.RoleType;
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
	private SaveDocumentReviewerPort saveDocumentReviewerPort;

	@Mock
	private LoadEmployeePort loadEmployeePort;

	private static Stream<Arguments> teamMembersAndPartLeadersAndTeamLeader() {
		Team team = new Team(1L, "team", false);
		List<User> teamMembers = List.of(UserTestData.createTeamMember(team));
		List<User> partLeaders = List.of(UserTestData.createPartLeader(team));
		User teamLeader = UserTestData.createTeamLeader(team);

		return Stream.of(
			Arguments.of(team, teamMembers, partLeaders, teamLeader)
		);
	}

	@ParameterizedTest
	@DisplayName("WorkingDocument 요청 성공")
	@MethodSource("teamMembersAndPartLeadersAndTeamLeader")
	void workingDocument_등록_성공(Team team, List<User> teamMembers, List<User> partLeaders, User teamLeader) {
		// given
		User assignee = UserTestData.createTeamLeader(team);
		Working working = WorkingTestData.createWorkingWithAssignee(assignee);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
		given(loadEmployeePort.loadByTeamAndRole(team, RoleType.PART_LEADER)).willReturn(partLeaders);
		given(loadEmployeePort.loadTeamLeaderByTeam(team)).willReturn(Optional.of(teamLeader));

		// when
		registerWorkingDocumentService.registerWorkingDocument(
			workingDocument, working, working.getAssignee(), teamMembers);

		// then
		int count = teamMembers.size() + partLeaders.size() + 1;
		verify(saveDocumentReviewerPort, times(count)).save(any(DocumentReviewer.class));
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
			List<User> reviewers = mock(List.class);

			// when, then
			assertThatThrownBy(() ->
				registerWorkingDocumentService.registerWorkingDocument(workingDocument, working, other, reviewers))
				.isInstanceOf(ForbiddenException.class);
		}

		@Test
		@DisplayName("WorkingDocument의 상태가 검토중(REVIEW)가 아니면 BadRequestException이 발생한다.")
		void 문서_상태_검증_테스트() {
			// given
			Working working = WorkingTestData.createWorkingWithStatus(WorkingStatusType.DONE);
			WorkingDocument invalidWorkingDocument = WorkingDocumentTestData.createWorkingDocumentWithStatus(
				DocStatusType.APPROVAL_REQUEST);
			List<User> reviewers = mock(List.class);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService
				.registerWorkingDocument(invalidWorkingDocument, working, working.getAssignee(), reviewers))
				.isInstanceOf(BadRequestException.class);
		}

		@Test
		@DisplayName("리뷰어가 없는 경우, BadRequestException이 발생한다.")
		void 리뷰어_검증_테스트() {
			// given
			Working working = WorkingTestData.createWorkingWithStatus(WorkingStatusType.DONE);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
			List<User> reviewers = List.of();

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService
				.registerWorkingDocument(workingDocument, working, working.getAssignee(), reviewers))
				.isInstanceOf(BadRequestException.class);
		}

		@Test
		@DisplayName("리뷰어로 본인을 지정한 경우, BadRequestException이 발생한다.")
		void 리뷰어_본인_검증_테스트() {
			// given
			User assignee = UserTestData.createTeamMember(mock(Team.class));
			Working working = WorkingTestData.createWorkingWithAssignee(assignee);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
			List<User> reviewers = List.of(assignee);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService
				.registerWorkingDocument(workingDocument, working, assignee, reviewers))
				.isInstanceOf(BadRequestException.class);
		}

		@Test
		@DisplayName("role이 TEAM_MEMBER가 아닌 리뷰어(PART_LEADER)가 존재할 경우, BadRequestException이 발생한다.")
		void 리뷰어_role_검증_테스트() {
			// given
			User assignee = UserTestData.createTeamMember(mock(Team.class));
			Working working = WorkingTestData.createWorkingWithAssignee(assignee);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
			User partLeader = UserTestData.createPartLeader(mock(Team.class));
			List<User> reviewers = List.of(partLeader);

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService
				.registerWorkingDocument(workingDocument, working, assignee, reviewers))
				.isInstanceOf(BadRequestException.class);
		}

		@Test
		@DisplayName("같은 팀이 아닌 리뷰어가 존재할 경우, BadRequestException이 발생한다.")
		void 리뷰어_팀_검증_테스트() {
			// given
			User assignee = UserTestData.createTeamMember(mock(Team.class));
			Working working = WorkingTestData.createWorkingWithAssignee(assignee);
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithWorking(working);
			List<User> reviewers = List.of(UserTestData.createTeamMember(mock(Team.class)));

			// when, then
			assertThatThrownBy(() -> registerWorkingDocumentService
				.registerWorkingDocument(workingDocument, working, assignee, reviewers))
				.isInstanceOf(BadRequestException.class);
		}
	}
}
