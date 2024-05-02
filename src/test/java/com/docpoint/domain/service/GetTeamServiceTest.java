package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadTeamPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;

@ExtendWith(MockitoExtension.class)
@DisplayName("팀 조회 서비스 테스트")
class GetTeamServiceTest {
	@InjectMocks
	private GetTeamService getTeamService;

	@Mock
	private LoadTeamPort loadTeamPort;

	@Test
	@DisplayName("팀이 없을 경우, NotFoundException 발생")
	void 조회_실패() {
		given(loadTeamPort.load(anyLong())).willReturn(Optional.empty());

		// when, then
		assertThatThrownBy(() -> getTeamService.getTeam(1L))
			.isInstanceOf(NotFoundException.class);
	}

	@Test
	@DisplayName("팀이 삭제된 경우, NotFoundException 발생")
	void 삭제된_팀_조회_실패() {
		Team deletedTeam = new Team(1L, "team", true);
		given(loadTeamPort.load(deletedTeam.getId())).willReturn(Optional.of(deletedTeam));

		// when, then
		assertThatThrownBy(() -> getTeamService.getTeam(1L))
			.isInstanceOf(NotFoundException.class);
	}

	@Test
	@DisplayName("팀 조회 성공")
	void 조회_성공() {
		Team team = new Team(1L, "team", false);
		given(loadTeamPort.load(team.getId())).willReturn(Optional.of(team));

		// when
		Team result = getTeamService.getTeam(1L);

		// then
		assertThat(result).isEqualTo(team);
	}
}
