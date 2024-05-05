package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface RegisterReviewUseCase {
	void registerReview(List<Review> reviews, User reviewer, WorkingDocument workingDocument);
}
