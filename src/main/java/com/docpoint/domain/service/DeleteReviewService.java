package com.docpoint.domain.service;

import com.docpoint.application.port.in.DeleteReviewUseCase;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteReviewService implements DeleteReviewUseCase {
	private final SaveReviewPort saveReviewPort;
	private final LoadReviewPort loadReviewPort;
	private final LoadDocumentReviewerPort loadDocumentReviewerPort;

	/**
	 * 리뷰 삭제
	 * @param workingDocument 삭제할 리뷰의 워킹 문서
	 * @param user 삭제 요청한 사용자
	 * @throws ForbiddenException 리뷰어가 아닌 경우
	 * @throws NotFoundException 리뷰가 존재하지 않는 경우
	 */
	@Override
	public void deleteReview(WorkingDocument workingDocument, User user) {
		DocumentReviewer documentReviewer = loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
		if (!loadReviewPort.existsReviewByReviewer(workingDocument, user)) {
			throw new NotFoundException(ErrorType.NOT_FOUND_REVIEW);
		}
		saveReviewPort.delete(documentReviewer);
	}
}
