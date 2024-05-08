package com.docpoint.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetReceivedRequestsUserCase;
import com.docpoint.application.port.out.LoadReceivedRequestPort;
import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetReceivedRequestsService implements GetReceivedRequestsUserCase {
	private final LoadReceivedRequestPort loadRequestedWorkingDocumentsPort;

	/**
	 * 요청 받은 WorkingReviewer 목록 조회
	 * @param user
	 * @param pageable
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<WorkingDocumentWithReviewDto> getReceivedRequests(User user, Pageable pageable) {
		return loadRequestedWorkingDocumentsPort.loadByUser(user, pageable);
	}
}
