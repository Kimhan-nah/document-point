package com.docpoint.application.port.out;

import com.docpoint.domain.model.Review;

public interface SaveReviewPort {
	Review save(Review review);
}
