package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface LoadReviewPort {
	List<Review> loadByWorkingDocument(WorkingDocument workingDocument);

	boolean existsReviewByReviewer(WorkingDocument workingDocument, User reviewer);

	List<Review> loadUserReviewOfDocument(DocumentReviewer documentReviewer);

	boolean existsReview(WorkingDocument workingDocument);
}
