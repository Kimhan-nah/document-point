package com.docpoint.application.port.out;

import com.docpoint.domain.entity.Review;

public interface SaveReviewPort {
	void save(Review review);
}
