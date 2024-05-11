package com.docpoint.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.adapter.in.dto.WorkingDocumentWithReviewDto;
import com.docpoint.application.port.in.GetReceivedRequestsUserCase;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReceivedRequestPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetReceivedRequestsService implements GetReceivedRequestsUserCase {
	private final LoadReceivedRequestPort loadRequestedWorkingDocumentsPort;
	private final LoadReviewPort loadReviewPort;
	private final LoadDocumentReviewerPort loadDocumentReviewerPort;

	/**
	 * 요청 받은 WorkingReviewer 목록 조회
	 * @param user
	 * @param pageable
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<WorkingDocumentWithReviewDto> getReceivedRequests(User user, Pageable pageable, DocStatusType status) {

		if (user.getRole() == RoleType.TEAM_LEADER) {
			Page<WorkingDocument> workingDocuments = loadRequestedWorkingDocumentsPort.loadByUserWithExcludeStatus(
				user, pageable, DocStatusType.REVIEW, status);
			List<WorkingDocumentWithReviewDto> content = workingDocuments.getContent()
				.stream()
				.map(WorkingDocumentWithReviewDto::toDto)
				.toList();

			return new PageImpl<>(content, pageable, workingDocuments.getTotalElements());
		}

		Page<WorkingDocument> workingDocuments = loadRequestedWorkingDocumentsPort.loadByUser(user, pageable, status);
		List<WorkingDocument> content = workingDocuments.getContent();
		List<WorkingDocumentWithReviewDto> dtoList = new ArrayList<>();
		for (WorkingDocument workingDocument : content) {
			dtoList.add(WorkingDocumentWithReviewDto.toDto(workingDocument,
				loadReviewPort.existsReviewByReviewer(workingDocument, user)));
		}
		return new PageImpl<>(dtoList, pageable, workingDocuments.getTotalElements());
	}
}
