package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.model.Review;

public interface LoadReviewsOfWorkingDocumentPort {
	List<Review> loadByWorkingDocumentId(long workingDocumentId);
}
