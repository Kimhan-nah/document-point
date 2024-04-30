package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;

public interface LoadReviewsPort {
	Optional<Review> load(long reviewId);

	List<Review> loadByWorkingDocument(WorkingDocument workingDocument);
}
