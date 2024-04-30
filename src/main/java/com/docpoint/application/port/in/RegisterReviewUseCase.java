package com.docpoint.application.port.in;

import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface RegisterReviewUseCase {
	void registerReview(Review review, User reviewer, WorkingDocument workingDocument);
}
