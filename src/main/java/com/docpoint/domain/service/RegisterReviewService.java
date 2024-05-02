package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.RegisterReviewUseCase;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadWorkingDocumentPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterReviewService implements RegisterReviewUseCase {
	private final LoadDocumentReviewerPort loadDocumentReviewerPort;

	private final LoadWorkingDocumentPort loadWorkingDocumentPort;

	private final SaveReviewPort saveReviewPort;

	/**
	 * review 등록
	 * @param review 등록할 review, documentReviewer는 null
	 * @param reviewer review를 등록할 사용자
	 * @param workingDocument review를 등록할 workingDocument
	 * @throws ForbiddenException 지정된 reivewer가 아닌 경우
	 */
	@Override
	@Transactional
	public void registerReview(Review review, User reviewer, WorkingDocument workingDocument) {
		DocumentReviewer documentReviewer = loadDocumentReviewerPort.loadByWorkingDocumentAndUser(
				workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));

		review = review.updateDocumentReviewer(documentReviewer);
		saveReviewPort.save(review);
	}
}
