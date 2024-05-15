package com.docpoint.domain.service;

import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadReceivedRequestPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("요청 받은 WorkingDocument 목록 조회")
class GetReceivedRequestsServiceTest {
	@InjectMocks
	private GetReceivedRequestsService getReceivedRequestsService;

	@Mock
	private LoadReceivedRequestPort loadRequestedWorkingDocumentsPort;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Test
	@DisplayName("팀 리더의 경우, 파트리더가 요청한 WorkingDocument 목록을 조회한다.")
	void 팀리더_받은_요청_조회() {
		User teamLeader = UserTestData.createTeamLeader(mock(Team.class));
		Pageable pageable = mock(Pageable.class);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		DocStatusType filterStatus = mock(DocStatusType.class);
		given(loadRequestedWorkingDocumentsPort.loadByUserWithExcludeStatus(
			teamLeader, pageable, DocStatusType.REVIEW, filterStatus))
			.willReturn(new PageImpl<>(List.of(workingDocument)));

		getReceivedRequestsService.getReceivedRequests(teamLeader, pageable, filterStatus);
	}

	@Test
	@DisplayName("파트 리더나 팀 멤버의 경우, 리뷰가 필요한 WorkingDocument 목록을 조회한다.")
	void 파트리더_팀멤버_받은_요청_조회() {
		User teamMember = UserTestData.createTeamMember(mock(Team.class));
		Pageable pageable = mock(Pageable.class);
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		DocStatusType filterStatus = mock(DocStatusType.class);
		given(loadRequestedWorkingDocumentsPort.loadByUser(teamMember, pageable, filterStatus))
			.willReturn(new PageImpl<>(List.of(workingDocument)));
		given(loadReviewPort.existsReviewByReviewer(workingDocument, teamMember))
			.willReturn(true);

		getReceivedRequestsService.getReceivedRequests(teamMember, pageable, filterStatus);
	}
}
