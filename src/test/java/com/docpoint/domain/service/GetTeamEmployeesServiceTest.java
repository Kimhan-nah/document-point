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

import com.docpoint.application.port.out.LoadEmployeesPort;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;
import com.docpoint.util.UserTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("팀 구성원 조회 서비스")
class GetTeamEmployeesServiceTest {
	@InjectMocks
	private GetTeamEmployeesService getTeamEmployeesService;

	@Mock
	private LoadEmployeesPort loadEmployeesPort;

	private Team team;
	private List<User> employees;
	private User teamMember;
	private User partLeader;
	private User teamLeader;

	@BeforeEach
	void setUp() {
		team = new Team(null, "team", false);
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
			given(loadEmployeesPort.loadByTeam(team)).willReturn(employees);

			// when
			List<User> teamMembers = getTeamEmployeesService.getEmployeesByTeam(team, null);

			// then
			assertThat(teamMembers).hasSize(3);
		}

		@Test
		@DisplayName("1명의 팀 멤버가 조회된다.")
		void getTeamMembers() {
			// given
			given(loadEmployeesPort.loadByTeamAndRole(team, RoleType.TEAM_MEMBER)).willReturn(List.of(teamMember));

			// when
			List<User> teamMembers = getTeamEmployeesService.getEmployeesByTeam(team, RoleType.TEAM_MEMBER);

			// then
			assertThat(teamMembers).hasSize(1);
			assertThat(teamMembers.get(0).getRole()).isEqualTo(RoleType.TEAM_MEMBER);
		}

		@Test
		@DisplayName("1명의 파트 리더가 조회된다.")
		void getPartLeaders() {
			// given
			given(loadEmployeesPort.loadByTeamAndRole(team, RoleType.PART_LEADER)).willReturn(List.of(partLeader));

			// when
			List<User> teamMembers = getTeamEmployeesService.getEmployeesByTeam(team, RoleType.PART_LEADER);

			// then
			assertThat(teamMembers).hasSize(1);
			assertThat(teamMembers.get(0).getRole()).isEqualTo(RoleType.PART_LEADER);
		}

		@Test
		@DisplayName("1명의 팀 리더가 조회된다.")
		void getTeamLeaders() {
			// given
			given(loadEmployeesPort.loadByTeamAndRole(team, RoleType.TEAM_LEADER)).willReturn(List.of(teamLeader));

			// when
			List<User> teamMembers = getTeamEmployeesService.getEmployeesByTeam(team, RoleType.TEAM_LEADER);

			// then
			assertThat(teamMembers).hasSize(1);
			assertThat(teamMembers.get(0).getRole()).isEqualTo(RoleType.TEAM_LEADER);
		}

	}

}
