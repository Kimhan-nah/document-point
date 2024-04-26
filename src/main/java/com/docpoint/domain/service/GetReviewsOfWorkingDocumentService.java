package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReviewsOfWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadReviewsOfWorkingDocumentPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.Review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetReviewsOfWorkingDocumentService implements GetReviewsOfWorkingDocumentUseCase {
	private final LoadReviewsOfWorkingDocumentPort loadReviewsOfWorkingDocumentPort;

	/**
	 * @param workingDocumentId 리뷰를 조회할 WorkingDocument
	 * @return WorkingDocument의 전체 리뷰
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Review> getAllReviews(long workingDocumentId) {
		return loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentId(workingDocumentId);
	}

	/**
	 * @param workingDocumentId 리뷰를 조회할 WorkingDocument
	 * @param reviewId       조회할 리뷰의 ID
	 * @return WorkingDocument의 특정 리뷰
	 */
	@Override
	@Transactional(readOnly = true)
	public Review getReview(long workingDocumentId, long reviewId) {
		return loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentIdAndReviewId(workingDocumentId, reviewId)
			.orElseThrow(() -> new NotFoundException("not found review"));
	}
}
