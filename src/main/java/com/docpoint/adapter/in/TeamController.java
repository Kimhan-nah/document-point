package com.docpoint.adapter.in;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.docpoint.adapter.in.dto.EmployeesResponseDto;
import com.docpoint.application.port.in.GetTeamEmployeesUseCase;
import com.docpoint.application.port.in.GetTeamUseCase;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

// @RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/teams")
public class TeamController {
	private final GetTeamEmployeesUseCase getTeamEmployeesUseCase;
	private final GetTeamUseCase getTeamUseCase;

	@GetMapping("/{teamId}/employees")
	ResponseEntity<EmployeesResponseDto> getEmployeesByTeamId(@PathVariable Long teamId,
		@RequestParam(required = false) RoleType role) {
		Team team = getTeamUseCase.getTeam(teamId);
		List<User> employeesByTeam = getTeamEmployeesUseCase.getEmployeesByTeam(team, role);
		return ResponseEntity.ok(EmployeesResponseDto.from(employeesByTeam));
	}
}
