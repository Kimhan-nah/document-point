package com.docpoint.domain.service;

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

import com.docpoint.application.port.out.LoadTeamEmployeesPort;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.User;
import com.docpoint.domain.type.RoleType;
import com.docpoint.util.UserTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("팀 구성원 조회 서비스")
class GetTeamEmployeesServiceTest {

	private static Team team;
	private static List<User> employees;
	private static User teamMember;
	private static User partLeader;
	private static User teamLeader;
	@InjectMocks
	private GetTeamEmployeesService getTeamEmployeesService;

	// TODO Mock 대신 Adapter를 사용하도록 수정
	@Mock
	private LoadTeamEmployeesPort loadTeamEmployeesPort;

	@BeforeEach
	void setUp() {
		team = new Team("team", false);
		employees = new ArrayList<>();
		teamMember = UserTestData.createTeamMember(team);
		partLeader = UserTestData.createPartLeader(team);
		teamLeader = UserTestData.createTeamLeader(team);
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