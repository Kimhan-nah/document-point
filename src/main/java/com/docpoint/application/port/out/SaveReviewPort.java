package com.docpoint.application.port.out;

import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Review;

public interface SaveReviewPort {
	void save(Review review);

	void delete(DocumentReviewer documentReviewer);
}
