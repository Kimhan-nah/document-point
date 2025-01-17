package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadWorkingDocumentPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument 조회 테스트")
class GetWorkingDocumentServiceTest {
	@InjectMocks
	private GetWorkingDocumentService getWorkingDocumentService;

	@Mock
	private LoadWorkingDocumentPort loadWorkingDocumentPort;

	@Test
	@DisplayName("WorkingDocument 조회 성공")
	void getWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.of(workingDocument));

		// when
		getWorkingDocumentService.getWorkingDocument(workingDocumentId);
	}

	@Test
	@DisplayName("WorkingDocument가 존재하지 않을 경우, NotFoundException 발생")
	void getWorkingDocumentFail() {
		// given
		long workingDocumentId = 1L;

		// when, then
		assertThatThrownBy(() -> getWorkingDocumentService.getWorkingDocument(workingDocumentId))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.NOT_FOUND_WORKING_DOCUMENT.getMessage());
	}

	@Test
	@DisplayName("삭제된 WorkingDocument일 경우, NotFoundException 발생")
	void 삭제된_WorkingDocument일_경우() {
		// given
		long workingDocumentId = 1L;
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		workingDocument.delete();

		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.of(workingDocument));

		// when, then
		assertThatThrownBy(() -> getWorkingDocumentService.getWorkingDocument(workingDocumentId))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.DELETED_WORKING_DOCUMENT.getMessage());
	}

	@Test
	@DisplayName("DocumentReviewer 목록 조회 성공 - TEAM_LEADER는 제외")
	void getDocumentReviewers() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User teamLeader = UserTestData.createTeamLeader(mock(Team.class));
		User partLeader = UserTestData.createPartLeader(mock(Team.class));
		User teamMember = UserTestData.createTeamMember(mock(Team.class));
		List<User> reviewers = List.of(teamLeader, partLeader, teamMember);
		given(loadWorkingDocumentPort.loadReviewers(workingDocument)).willReturn(reviewers);

		// when
		var documentReviewers = getWorkingDocumentService.getDocumentReviewers(workingDocument);

		// then
		assertThat(documentReviewers).hasSize(2);
		assertThat(documentReviewers).allMatch(user -> user.getRole() != RoleType.TEAM_LEADER);
	}
}
