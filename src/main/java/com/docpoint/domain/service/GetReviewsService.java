package com.docpoint.domain.service;

import java.util.List;

import com.docpoint.application.port.in.GetReviewsUseCase;
import com.docpoint.application.port.out.LoadReviewsOfWorkingDocumentPort;
import com.docpoint.domain.model.Review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetReviewsService implements GetReviewsUseCase {
	private final LoadReviewsOfWorkingDocumentPort loadReviewsOfWorkingDocumentPort;

	/**
	 * @param workingDocumentId 리뷰를 조회할 WorkingDocument
	 * @return WorkingDocument의 전체 리뷰
	 */
	@Override
	public List<Review> getReviewsOfWorkingDocument(long workingDocumentId) {
		return loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentId(workingDocumentId);
	}
}
