package com.docpoint.application.port.in;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface DeleteReviewUseCase {
	void deleteReview(WorkingDocument workingDocument, User user);
}
