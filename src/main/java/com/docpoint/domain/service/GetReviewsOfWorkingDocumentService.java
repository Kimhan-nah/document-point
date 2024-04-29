package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReviewsOfWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadReviewsOfWorkingDocumentPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetReviewsOfWorkingDocumentService implements GetReviewsOfWorkingDocumentUseCase {
	private final LoadReviewsOfWorkingDocumentPort loadReviewsOfWorkingDocumentPort;
	private final LoadWorkingDocumentsPort loadWorkingDocumentPort;

	/**
	 * @param workingDocumentId 리뷰를 조회할 WorkingDocument
	 * @return WorkingDocument의 전체 리뷰
	 * @throws NotFoundException WorkingDocument가 존재하지 않을 경우
	 * @throws NotFoundException 삭제된 WorkingDocument일 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Review> getAllReviews(long workingDocumentId) {
		WorkingDocument workingDocument = loadWorkingDocumentPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT));
		if (workingDocument.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_WORKING_DOCUMENT);
		}
		return loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentId(workingDocumentId);
	}

	/**
	 * @param workingDocumentId 리뷰를 조회할 WorkingDocument
	 * @param reviewId       조회할 리뷰의 ID
	 * @return WorkingDocument의 특정 리뷰
	 * @throws NotFoundException WorkingDocument 또는 Review가 존재하지 않을 경우
	 * @throws NotFoundException 삭제된 WorkingDocument일 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public Review getReview(long workingDocumentId, long reviewId) {
		WorkingDocument workingDocument = loadWorkingDocumentPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT));
		if (workingDocument.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_WORKING_DOCUMENT);
		}
		Review review = loadReviewsOfWorkingDocumentPort.loadByReviewId(reviewId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_REVIEW));
		if (review.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_REVIEW);
		}
		return review;
	}
}
