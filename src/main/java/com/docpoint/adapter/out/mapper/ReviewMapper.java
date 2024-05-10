package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Review;
import com.docpoint.infrastructure.entity.ReviewJpaEntity;

public class ReviewMapper {
	public static Review mapToDomainEntity(ReviewJpaEntity review) {
		return Review.builder()
			.id(review.getId())
			.documentReviewer(review.isDocumentReviewerEmpty() ? null :
				DocumentReviewerMapper.mapToDomainEntity(review.getDocumentReviewer()))
			.question(review.getQuestion())
			.answer(review.getAnswer())
			.isDeleted(review.getIsDeleted())
			.build();
	}

	public static ReviewJpaEntity mapToJpaEntity(Review review) {
		DocumentReviewer documentReviewer = review.getDocumentReviewer();
		return ReviewJpaEntity.builder()
			.id(review.getId())
			.documentReviewer(
				review.isDocumentReviewerEmpty() ? null :
					DocumentReviewerMapper.mapToJpaEntity(documentReviewer))
			.question(review.getQuestion())
			.answer(review.getAnswer())
			.isDeleted(review.isDeleted())
			.build();
	}
}
