package com.docpoint.adapter.in.dto;

import java.util.List;

import com.docpoint.domain.entity.Evaluation;

import lombok.Getter;

@Getter
public class ReviewResponseDto {
	private final UserDto reviewer;
	private final List<Evaluation> review;

	public ReviewResponseDto(UserDto reviewer, List<Evaluation> review) {
		this.reviewer = reviewer;
		this.review = review;
	}

	public static ReviewResponseDto of(UserDto reviewer, List<Evaluation> review) {
		return new ReviewResponseDto(reviewer, review);
	}
}
