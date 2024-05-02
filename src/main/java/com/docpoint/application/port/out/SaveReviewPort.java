package com.docpoint.application.port.out;

import com.docpoint.domain.entity.Review;

public interface SaveReviewPort {
	Review save(Review review);
}
