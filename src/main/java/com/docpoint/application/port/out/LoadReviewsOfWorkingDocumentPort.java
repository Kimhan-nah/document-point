package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.Review;

public interface LoadReviewsOfWorkingDocumentPort {
	List<Review> loadByWorkingDocumentId(long workingDocumentId);

	Optional<Review> loadByReviewId(long reviewId);
}
