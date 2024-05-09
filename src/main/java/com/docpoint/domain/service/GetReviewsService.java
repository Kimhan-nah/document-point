package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReviewsUseCase;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetReviewsService implements GetReviewsUseCase {
	private final LoadReviewPort loadReviewPort;

	/**
	 * @param workingDocument 리뷰를 조회할 WorkingDocument
	 * @return WorkingDocument의 전체 리뷰
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Review> getAllReviews(WorkingDocument workingDocument, User user) {
		if (user.getRole() == RoleType.TEAM_MEMBER && !workingDocument.getWorking().getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_ASSIGNEE);
		}
		return loadReviewPort.loadByWorkingDocument(workingDocument);
	}

	/**
	 * @param reviewId       조회할 리뷰의 ID
	 * @return WorkingDocument의 특정 리뷰
	 * @throws NotFoundException Review가 존재하지 않을 경우
	 * @throws NotFoundException Review가 삭제된 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public Review getReview(long reviewId) {
		Review review = loadReviewPort.load(reviewId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_REVIEW));
		if (review.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_REVIEW);
		}
		return review;
	}
}
