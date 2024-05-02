package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.WorkingDocument;

public interface GetReviewsUseCase {
	List<Review> getAllReviews(WorkingDocument workingDocument);

	Review getReview(long reviewId);
}
