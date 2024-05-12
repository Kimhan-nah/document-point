package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface RegisterReviewUseCase {
	void registerReview(List<Evaluation> review, User reviewer, WorkingDocument workingDocument);

	void updateReview(List<Evaluation> review, User reviewer, WorkingDocument workingDocument);
}
