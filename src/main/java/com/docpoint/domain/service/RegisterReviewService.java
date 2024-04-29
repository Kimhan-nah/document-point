package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.RegisterReviewUseCase;
import com.docpoint.application.port.out.LoadDocumentReviewersPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.DocumentReviewer;
import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterReviewService implements RegisterReviewUseCase {
	private final LoadDocumentReviewersPort loadDocumentReviewersPort;

	private final LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	private final SaveReviewPort saveReviewPort;

	/**
	 * review 등록
	 * @param review 등록할 review, workingDocument는 null
	 * @param reviewer review를 등록할 사용자
	 * @param workingDocumentId
	 * @throws NotFoundException workingDocumentId에 해당하는 workingDocument가 없을 경우
	 * @throws ForbiddenException 지정된 reivewer가 아닌 경우
	 */
	@Override
	@Transactional
	public void registerReview(Review review, User reviewer, long workingDocumentId) {
		loadWorkingDocumentsPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT));

		DocumentReviewer documentReviewer = loadDocumentReviewersPort.loadByWorkingDocumentIdAndUserId(
				workingDocumentId, reviewer.getId())
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));

		review = review.updateDocumentReviewer(documentReviewer);
		saveReviewPort.save(review);
	}
}
