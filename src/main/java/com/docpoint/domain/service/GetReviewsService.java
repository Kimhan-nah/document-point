package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReviewsUseCase;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.DocumentReviewer;
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
	public List<Review> getAllReviews(WorkingDocument workingDocument, User user) {
		if (user.getRole() == RoleType.TEAM_MEMBER && !workingDocument.getWorking().getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_ASSIGNEE);
		}
		return loadReviewPort.loadByWorkingDocument(workingDocument);
	}

	/**
	 * @param workingDocument
	 * @param user
	 * @return
	 * @throws ForbiddenException 리뷰어가 아닌 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Review> getReview(WorkingDocument workingDocument, User user) {
		DocumentReviewer documentReviewer = loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
		return loadReviewPort.loadUserReviewOfDocument(documentReviewer);
	}
}
