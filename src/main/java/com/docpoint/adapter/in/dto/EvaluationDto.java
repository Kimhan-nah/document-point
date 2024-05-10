package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import lombok.Getter;

@Getter
public class EvaluationDto {
	private final ReviewQuestionType reviewQuestion;
	private final ReviewAnswerType reviewAnswer;

	public EvaluationDto(ReviewQuestionType reviewQuestion, ReviewAnswerType reviewAnswer) {
		this.reviewQuestion = reviewQuestion;
		this.reviewAnswer = reviewAnswer;
	}

	public static EvaluationDto toDto(Review review) {
		return new EvaluationDto(review.getQuestion(), review.getAnswer());
	}
}
