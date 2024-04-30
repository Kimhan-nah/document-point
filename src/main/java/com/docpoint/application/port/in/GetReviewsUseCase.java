package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;

public interface GetReviewsUseCase {
	List<Review> getAllReviews(WorkingDocument workingDocument);

	Review getReview(long reviewId);
}
