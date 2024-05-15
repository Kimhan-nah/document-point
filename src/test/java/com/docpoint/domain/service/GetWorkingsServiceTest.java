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

import com.docpoint.application.port.out.LoadUserWorkingPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("워킹 목록 조회 테스트")
class GetWorkingsServiceTest {
	@InjectMocks
	private GetWorkingsService getWorkingsService;

	@Mock
	private LoadUserWorkingPort loadUserWorkingPort;

	@Test
	@DisplayName("조회 성공 - 검색 결과가 없을 경우 빈 목록을 반환한다.")
	void 조회_성공() {
		User user = UserTestData.createTeamMember(new Team(null, "team", false));
		given(loadUserWorkingPort.loadByTitle(user.getId(), "검색어"))
			.willReturn(List.of());

		List<Working> workings = getWorkingsService.getWorkingsByTitle(user, "검색어");

		assertThat(workings).isEmpty();
	}

	@Test
	@DisplayName("Working이 없는 경우, NotFoundException 발생")
	void Working이_없는_경우() {
		given(loadUserWorkingPort.load(anyLong())).willReturn(Optional.empty());

		assertThatThrownBy(() -> getWorkingsService.getWorkingById(anyLong()))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.NOT_FOUND_WORKING.getMessage());
	}

	@Test
	@DisplayName("삭제된 Working인 경우, NotFoundException 발생")
	void 삭제된_Working인_경우() {
		Working working = WorkingTestData.createDeletedWorking();
		given(loadUserWorkingPort.load(anyLong())).willReturn(Optional.of(working));

		assertThatThrownBy(() -> getWorkingsService.getWorkingById(anyLong()))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.DELETED_WORKING.getMessage());
	}

	@Test
	@DisplayName("Working 조회 성공")
	void Working_조회_성공() {
		Working working = WorkingTestData.createWorking();
		given(loadUserWorkingPort.load(anyLong())).willReturn(Optional.of(working));

		Working result = getWorkingsService.getWorkingById(anyLong());

		assertThat(result).isEqualTo(working);
	}

}
