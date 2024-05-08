package com.docpoint.adapter.in;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.docpoint.adapter.in.dto.WorkingDto;
import com.docpoint.adapter.in.dto.WorkingResponseDto;
import com.docpoint.application.port.in.GetWorkingsUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/workings")
public class WorkingController {
	private final GetWorkingsUseCase getWorkingUseCase;

	@GetMapping
	public ResponseEntity<WorkingResponseDto> getWorking(@RequestParam String search, @LoginUser User user) {
		List<Working> workings = getWorkingUseCase.getWorkingsByTitle(user, search);
		return ResponseEntity.ok(WorkingResponseDto.from(
			workings.stream().map(WorkingDto::toDto).toList()
		));
	}
}
