package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.Review;

public interface GetReviewsUseCase {
	List<Review> getReviewsOfWorkingDocument(long workingDocumentId);
}
