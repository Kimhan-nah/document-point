package com.docpoint.user.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.user.application.port.out.LoadTeamEmployeesPort;
import com.docpoint.user.domain.entity.Team;
import com.docpoint.user.domain.entity.User;
import com.docpoint.user.domain.type.RoleType;
import com.docpoint.user.util.UserTestData;

@ExtendWith(MockitoExtension.class)
class GetTeamEmployeesServiceTest {
	Team team = new Team("team");
	List<User> employees = new ArrayList<>();
	User teamMember = UserTestData.createTeamMember(team);
	User partLeader = UserTestData.createPartLeader(team);
	User teamLeader = UserTestData.createTeamLeader(team);

	@InjectMocks
	private GetTeamEmployeesService getTeamEmployeesService;
	@Mock
	private LoadTeamEmployeesPort loadTeamEmployeesPort;

	@BeforeEach
	void setUp() {
		employees.add(teamMember);
		employees.add(partLeader);
		employees.add(teamLeader);
	}

	@Nested
	@DisplayName("팀 멤버, 파트 리더, 팀 리더 3명의 구성원에 대한 전체 조회시")
	class GetTeamEmployees {
		@Test
		@DisplayName("3명의 구성원이 모두 조회된다.")
		void getTeamEmployees() {
			// given
			given(loadTeamEmployeesPort.loadTeamEmployees(team)).willReturn(employees);

			// when
			List<User> teamMembers = getTeamEmployeesService.getEmployeesByTeam(team);

			// then
			assertThat(teamMembers).hasSize(3);
		}

		@Test
		@DisplayName("1명의 팀 멤버가 조회된다.")
		void getTeamMembers() {
			// given
			given(loadTeamEmployeesPort.loadTeamMembers(team)).willReturn(List.of(teamMember));

			// when
			List<User> teamMembers = getTeamEmployeesService.getTeamMembersByTeam(team);

			// then
			assertThat(teamMembers).hasSize(1);
			assertThat(teamMembers.get(0).getRole()).isEqualTo(RoleType.TEAM_MEMBER);
		}

		@Test
		@DisplayName("1명의 파트 리더가 조회된다.")
		void getPartLeaders() {
			// given
			given(loadTeamEmployeesPort.loadPartLeaders(team)).willReturn(List.of(partLeader));

			// when
			List<User> teamMembers = getTeamEmployeesService.getPartLeadersByTeam(team);

			// then
			assertThat(teamMembers).hasSize(1);
			assertThat(teamMembers.get(0).getRole()).isEqualTo(RoleType.PART_LEADER);
		}

		@Test
		@DisplayName("1명의 팀 리더가 조회된다.")
		void getTeamLeaders() {
			// given
			given(loadTeamEmployeesPort.loadTeamLeaders(team)).willReturn(List.of(teamLeader));

			// when
			List<User> teamMembers = getTeamEmployeesService.getTeamLeadersByTeam(team);

			// then
			assertThat(teamMembers).hasSize(1);
			assertThat(teamMembers.get(0).getRole()).isEqualTo(RoleType.TEAM_LEADER);
		}

	}

}
