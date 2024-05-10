package com.docpoint.adapter.in.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class ReviewResponseDto {
	private final UserDto reviewer;
	private final List<EvaluationDto> evaluations;

	public ReviewResponseDto(UserDto reviewer, List<EvaluationDto> evaluations) {
		this.reviewer = reviewer;
		this.evaluations = evaluations;
	}

}
