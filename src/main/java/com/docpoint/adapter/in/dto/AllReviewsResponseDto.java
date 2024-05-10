package com.docpoint.adapter.in.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class AllReviewsResponseDto {
	private final List<ReviewResponseDto> allReviews;

	public AllReviewsResponseDto(List<ReviewResponseDto> allReviews) {
		this.allReviews = allReviews;
	}

	public static AllReviewsResponseDto of(List<ReviewResponseDto> allReviews) {
		return new AllReviewsResponseDto(allReviews);
	}
}
