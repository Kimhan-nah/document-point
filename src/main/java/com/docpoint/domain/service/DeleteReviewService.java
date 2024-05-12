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
