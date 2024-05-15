package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadUserPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.util.UserTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("유저 정보 조회 테스트")
class GetUserServiceTest {
	@InjectMocks
	private GetUserService getUserService;

	@Mock
	private LoadUserPort loadUserPort;

	@Test
	@DisplayName("유저가 존재하지 않을 경우, NotFoundException 발생")
	void 존재하지_않는_유저() {
		given(loadUserPort.loadById(anyLong())).willReturn(Optional.empty());

		assertThatThrownBy(() -> getUserService.getUserById(anyLong()))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.NOT_FOUND_USER.getMessage());
	}

	@Test
	@DisplayName("삭제된 유저일 경우, NotFoundException 발생")
	void 삭제된_유저() {
		User user = UserTestData.createTeamLeader(mock(Team.class));
		user.delete();
		given(loadUserPort.loadById(anyLong())).willReturn(Optional.of(user));

		assertThatThrownBy(() -> getUserService.getUserById(anyLong()))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.NOT_FOUND_USER.getMessage());
	}

	@Test
	@DisplayName("유저 조회 성공")
	void 유저_조회_성공() {
		User user = UserTestData.createTeamLeader(mock(Team.class));
		given(loadUserPort.loadById(anyLong())).willReturn(Optional.of(user));

		User result = getUserService.getUserById(anyLong());

		assertThat(result).isEqualTo(user);
	}

	@Test
	@DisplayName("유저 목록 조회 성공")
	void 유저_목록_조회_성공() {
		User user = UserTestData.createTeamLeader(mock(Team.class));
		List<Long> userIds = List.of(user.getId());
		given(loadUserPort.loadById(user.getId())).willReturn(Optional.of(user));

		assertThatCode(() -> getUserService.getUsers(userIds)).doesNotThrowAnyException();
	}
}
