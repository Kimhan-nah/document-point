package com.docpoint.domain.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.in.GetReceivedRequestsUserCase;
import com.docpoint.application.port.out.LoadReceivedRequestsPort;
import com.docpoint.domain.model.DocumentReview;
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
	public Map<WorkingDocument, Optional<DocumentReview>> getReceivedRequests(Long userId, Pageable pageable) {
		return loadRequestedWorkingDocumentsPort.loadByUserId(userId, pageable);
	}
}
