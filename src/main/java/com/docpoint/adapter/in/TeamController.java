package com.docpoint.adapter.in;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.docpoint.adapter.in.dto.EmployeesResponseDto;
import com.docpoint.adapter.in.dto.WorkingDocumentDto;
import com.docpoint.adapter.in.dto.WorkingDocumentsResponseDto;
import com.docpoint.application.port.in.GetAllWorkingDocumentsUseCase;
import com.docpoint.application.port.in.GetTeamEmployeesUseCase;
import com.docpoint.application.port.in.GetTeamUseCase;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/teams")
public class TeamController {
	private final GetTeamEmployeesUseCase getTeamEmployeesUseCase;
	private final GetTeamUseCase getTeamUseCase;
	private final GetAllWorkingDocumentsUseCase getAllWorkingDocumentsUseCase;

	@GetMapping("/{teamId}/employees")
	ResponseEntity<EmployeesResponseDto> getEmployeesByTeamId(@PathVariable @Positive Long teamId,
		@RequestParam(required = false) RoleType role) {
		Team team = getTeamUseCase.getTeam(teamId);
		List<User> employeesByTeam = getTeamEmployeesUseCase.getEmployeesByTeam(team, role);
		return ResponseEntity.ok(EmployeesResponseDto.from(employeesByTeam));
	}

	@GetMapping("/{teamId}/workingdocs")
	ResponseEntity<WorkingDocumentsResponseDto> getTeamWorkingDocs(@PathVariable @Positive Long teamId,
		@PageableDefault Pageable pageable) {
		Page<WorkingDocument> workingDocuments = getAllWorkingDocumentsUseCase.getAllWorkingDocumentsByTeamId(teamId,
			pageable);
		int totalPage = workingDocuments.getTotalPages();
		List<WorkingDocumentDto> list = workingDocuments.stream().map(WorkingDocumentDto::toDto).toList();
		return ResponseEntity.ok(new WorkingDocumentsResponseDto(list, totalPage));
	}
}
