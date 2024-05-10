package com.docpoint.application.port.in;

import java.util.List;
import java.util.Map;

import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface GetReviewsUseCase {

	Map<User, List<Evaluation>> getAllReviews(WorkingDocument workingDocument, User user);

	List<Evaluation> getReview(WorkingDocument workingDocument, User user);
}
