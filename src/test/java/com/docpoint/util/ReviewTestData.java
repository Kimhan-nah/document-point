package com.docpoint.util;

import java.util.List;

import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.ReviewJpaEntity;

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

	public static Review createReview() {
		return Review.builder()
			.question(ReviewQuestionType.CLARITY)
			.answer(ReviewAnswerType.BAD)
			.build();
	}

	public static List<ReviewJpaEntity> createReviewJpaEntity(DocumentReviewerJpaEntity documentReviewer) {
		return List.of(
			ReviewJpaEntity.builder()
				.question(ReviewQuestionType.CLARITY)
				.answer(ReviewAnswerType.BAD)
				.documentReviewer(documentReviewer)
				.isDeleted(false)
				.build(),
			ReviewJpaEntity.builder()
				.question(ReviewQuestionType.COMPLETENESS)
				.answer(ReviewAnswerType.GOOD)
				.documentReviewer(documentReviewer)
				.isDeleted(false)
				.build(),
			ReviewJpaEntity.builder()
				.question(ReviewQuestionType.CONSISTENCY)
				.answer(ReviewAnswerType.NORMAL)
				.documentReviewer(documentReviewer)
				.isDeleted(false)
				.build()
		);
	}
}
