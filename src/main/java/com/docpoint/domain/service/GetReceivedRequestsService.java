package com.docpoint.domain.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReceivedRequestsUserCase;
import com.docpoint.application.port.out.LoadReceivedRequestsPort;
import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetReceivedRequestsService implements GetReceivedRequestsUserCase {
	private final LoadReceivedRequestsPort loadRequestedWorkingDocumentsPort;

	/**
	 * 요청 받은 WorkingReviewer 목록 조회
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<WorkingDocument, Optional<Review>> getReceivedRequests(long userId, Pageable pageable) {
		return loadRequestedWorkingDocumentsPort.loadByUserId(userId, pageable);
	}
}
