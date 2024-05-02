package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.WorkingDocument;

public interface LoadReviewsPort {
	Optional<Review> load(long reviewId);

	List<Review> loadByWorkingDocument(WorkingDocument workingDocument);
}
