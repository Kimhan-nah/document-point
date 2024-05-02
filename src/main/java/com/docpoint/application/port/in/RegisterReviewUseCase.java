package com.docpoint.application.port.in;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface RegisterReviewUseCase {
	void registerReview(Review review, User reviewer, WorkingDocument workingDocument);
}
