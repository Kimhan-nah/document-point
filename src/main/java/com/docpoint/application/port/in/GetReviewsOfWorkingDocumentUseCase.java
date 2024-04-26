package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.Review;

public interface GetReviewsOfWorkingDocumentUseCase {
	List<Review> getAllReviews(long workingDocumentId);

	Review getReview(long workingDocumentId, long reviewId);
}
