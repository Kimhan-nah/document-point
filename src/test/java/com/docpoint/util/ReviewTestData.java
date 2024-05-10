package com.docpoint.util;

import java.util.List;

import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

public class ReviewTestData {
	public static List<Evaluation> createReviewsWithoutReviewer() {
		return List.of(
			Evaluation.builder()
				.question(ReviewQuestionType.CLARITY)
				.answer(ReviewAnswerType.BAD)
				.build(),
			Evaluation.builder()
				.question(ReviewQuestionType.COMPLETENESS)
				.answer(ReviewAnswerType.GOOD)
				.build(),
			Evaluation.builder()
				.question(ReviewQuestionType.CONSISTENCY)
				.answer(ReviewAnswerType.NORMAL)
				.build()
		);
	}
}
