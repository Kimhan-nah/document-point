package com.docpoint.adapter.in;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docpoint.adapter.in.dto.CpDto;
import com.docpoint.adapter.in.dto.CpRequestDto;
import com.docpoint.adapter.in.dto.CpResponseDto;
import com.docpoint.application.port.in.GetCpEvaluationsUseCase;
import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.application.port.in.RegisterCpEvaluationUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/workingdocs/{workingId}/cp")
public class CpController {
	private final GetCpEvaluationsUseCase getCpEvaluationsUseCase;
	private final GetWorkingDocumentUseCase getWorkingDocumentUseCase;
	private final RegisterCpEvaluationUseCase registerCpEvaluationUseCase;

	@GetMapping
	ResponseEntity<CpResponseDto> getCp(@PathVariable @Positive Long workingId, @LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		Map<RoleType, CpEvaluation> cpEvaluations = getCpEvaluationsUseCase.getCpEvaluations(user, workingDocument);

		CpDto request;
		CpDto approval = CpDto.toDto(user.getRole(), cpEvaluations.get(RoleType.TEAM_LEADER));
		if (user.getRole() == RoleType.TEAM_MEMBER) {
			request = null;
		} else {
			request = CpDto.toDto(user.getRole(), cpEvaluations.get(RoleType.PART_LEADER));
		}

		return ResponseEntity.ok(new CpResponseDto(request, approval));
	}

	@PostMapping
	ResponseEntity<Void> registerCp(@PathVariable @Positive Long workingId,
		@RequestBody @Valid CpRequestDto cpRequestDto,
		@LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		CpEvaluation cpEvaluation = CpEvaluation.builder()
			.cp(cpRequestDto.getCp())
			.comment(cpRequestDto.getComment())
			.build();

		registerCpEvaluationUseCase.registerCpEvaluation(cpEvaluation, workingDocument, user);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
