package com.docpoint.util;

import java.util.List;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

public class ReviewTestData {
	public static List<Review> createReviewsWithoutReviewer() {
		return List.of(
			Review.builder()
				.question(ReviewQuestionType.CLARITY)
				.answer(ReviewAnswerType.BAD)
				.isDeleted(false)
				.build(),
			Review.builder()
				.question(ReviewQuestionType.COMPLETENESS)
				.answer(ReviewAnswerType.GOOD)
				.isDeleted(false)
				.build(),
			Review.builder()
				.question(ReviewQuestionType.CONSISTENCY)
				.answer(ReviewAnswerType.NORMAL)
				.isDeleted(false)
				.build()
		);
	}
}
