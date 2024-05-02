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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadTeamPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.domain.entity.Team;

@ExtendWith(MockitoExtension.class)
@DisplayName("팀의 전체 워킹 문서 조회")
class GetAllWorkingDocumentsServiceTest {
	@InjectMocks
	private GetAllWorkingDocumentsService getAllWorkingDocumentsService;

	@Mock
	private LoadTeamPort loadTeamPort;

	@Mock
	private LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	@Test
	@DisplayName("조회 성공")
	void 조회_성공() {
		// given
		Team team = new Team(null, "team", false);
		long teamId = 1L;
		Pageable pageable = mock(Pageable.class);
		given(loadTeamPort.load(teamId)).willReturn(Optional.of(team));
		given(loadWorkingDocumentsPort.loadByTeamId(teamId, pageable)).willReturn(Page.empty());

		// when
		getAllWorkingDocumentsService.getAllWorkingDocumentsByTeamId(teamId, pageable);

		// then
		verify(loadWorkingDocumentsPort, times(1)).loadByTeamId(teamId, pageable);
	}

	@Nested
	@DisplayName("팀의 전체 워킹 문서 조회 실패")
	class GetAllWorkingDocumentsFail {
		@Test
		@DisplayName("존재하지 않는 팀인 경우, CustomRuntimeException이 발생한다.")
		void 존재하지_않는_팀() {
			// given
			long notFoundTeamId = 1L;
			given(loadTeamPort.load(notFoundTeamId)).willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(
				() -> getAllWorkingDocumentsService.getAllWorkingDocumentsByTeamId(notFoundTeamId,
					mock(Pageable.class)))
				.isInstanceOf(CustomRuntimeException.class);
		}

		@Test
		@DisplayName("삭제된 팀인 경우, CustomRuntimeException이 발생한다.")
		void 삭제된_팀() {
			// given
			long deletedTeamId = 1L;
			Team deletedTeam = new Team(deletedTeamId, "deletedTeam", true);
			given(loadTeamPort.load(deletedTeamId)).willReturn(Optional.of(deletedTeam));

			// when, then
			assertThatThrownBy(
				() -> getAllWorkingDocumentsService.getAllWorkingDocumentsByTeamId(deletedTeamId, mock(Pageable.class)))
				.isInstanceOf(CustomRuntimeException.class);
		}
	}
}
