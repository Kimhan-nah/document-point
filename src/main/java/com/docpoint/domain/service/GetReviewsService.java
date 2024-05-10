package com.docpoint.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReviewsUseCase;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetReviewsService implements GetReviewsUseCase {
	private final LoadReviewPort loadReviewPort;
	private final LoadDocumentReviewerPort loadDocumentReviewerPort;

	/**
	 * @param workingDocument 리뷰를 조회할 WorkingDocument
	 * @return WorkingDocument의 전체 리뷰
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<User, List<Evaluation>> getAllReviews(WorkingDocument workingDocument, User user) {
		Map<User, List<Evaluation>> allReviews = new HashMap<>();

		if (user.getRole() == RoleType.TEAM_MEMBER && !workingDocument.getWorking().getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_ASSIGNEE);
		}

		List<DocumentReviewer> documentReviewers = loadDocumentReviewerPort.loadByWorkingDocumentId(
			workingDocument.getId());
		for (DocumentReviewer documentReviewer : documentReviewers) {
			List<Evaluation> review = loadReviewPort.loadUserReviewOfDocument(documentReviewer)
				.stream()
				.map(Review -> Evaluation.of(Review.getQuestion(), Review.getAnswer()))
				.toList();
			if (!review.isEmpty()) {
				allReviews.put(documentReviewer.getReviewer(), review);
			}
		}

		return allReviews;
	}

	/**
	 * @param workingDocument
	 * @param user
	 * @return
	 * @throws ForbiddenException 리뷰어가 아닌 경우
	 * @throws NotFoundException   리뷰가 없는 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Evaluation> getReview(WorkingDocument workingDocument, User user) {
		DocumentReviewer documentReviewer = loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));

		List<Review> reviews = loadReviewPort.loadUserReviewOfDocument(documentReviewer);
		if (reviews.isEmpty()) {
			throw new NotFoundException(ErrorType.NOT_FOUND_REVIEW);
		}
		return reviews
			.stream()
			.map(Review -> Evaluation.of(Review.getQuestion(), Review.getAnswer()))
			.toList();
	}
}
