package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadUserWorkingsPort;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.util.UserTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("워킹 목록 조회 테스트")
class GetWorkingsServiceTest {
	@InjectMocks
	private GetWorkingsService getWorkingsService;

	@Mock
	private LoadUserWorkingsPort loadUserWorkingsPort;

	@Test
	@DisplayName("조회 성공 - 검색 결과가 없을 경우 빈 목록을 반환한다.")
	void 조회_성공() {
		User user = UserTestData.createTeamMember(new Team("team", false));
		given(loadUserWorkingsPort.loadByStatusIsNotAndTitle(user.getId(), WorkingStatusType.WAITING, "검색어"))
			.willReturn(List.of());

		List<Working> workings = getWorkingsService.getWorkingsByTitle(user, "검색어");

		assertThat(workings).isEmpty();
	}

}
