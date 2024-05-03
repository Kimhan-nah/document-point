package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.Review;
import com.docpoint.infrastructure.entity.ReviewJpaEntity;

public class ReviewMapper {
	public static Review mapToDomainEntity(ReviewJpaEntity review) {
		return Review.builder()
			.id(review.getId())
			.documentReviewer(review.isDocumentReviewerEmpty() ? null :
				DocumentReviewerMapper.mapToDomainEntity(review.getDocumentReviewer()))
			.isDeleted(review.getIsDeleted())
			.build();
	}
}
