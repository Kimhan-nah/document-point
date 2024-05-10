package com.docpoint.adapter.in.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.type.ReviewQuestionType;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDto {
	@NotNull(message = "review는 필수입니다.")
	private List<Evaluation> review;

	public ReviewRequestDto(List<Evaluation> review) {
		this.review = review;
	}

	@AssertTrue(message = "잘못된 review입니다.")
	public boolean isValidReview() {
		if (review != null && review.size() != ReviewQuestionType.values().length) {
			return false;
		}
		Set<ReviewQuestionType> evaluations = new HashSet<>();
		for (Evaluation evaluation : review) {
			if (evaluation.getQuestion() == null || evaluation.getAnswer() == null) {
				return false;
			}
			evaluations.add(evaluation.getQuestion());
		}

		return evaluations.size() == ReviewQuestionType.values().length;
	}
}
