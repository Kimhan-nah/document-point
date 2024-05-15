package com.docpoint.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.adapter.in.dto.WorkingDocumentWithReviewDto;
import com.docpoint.application.port.in.GetReceivedRequestsUserCase;
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
			return getReceivedRequestsOfTeamLeader(user, pageable, status);
		}

		Page<WorkingDocument> workingDocuments = loadRequestedWorkingDocumentsPort.loadByUser(user, pageable, status);
		List<WorkingDocumentWithReviewDto> content = workingDocuments.getContent()
			.stream()
			.map((WorkingDocument workingDocument) -> {
				boolean isReviewed = loadReviewPort.existsReviewByReviewer(workingDocument, user);
				return WorkingDocumentWithReviewDto.toDto(workingDocument, isReviewed);
			})
			.toList();

		return new PageImpl<>(content, pageable, workingDocuments.getTotalElements());
	}

	private Page<WorkingDocumentWithReviewDto> getReceivedRequestsOfTeamLeader(User user, Pageable pageable,
		DocStatusType status) {
		Page<WorkingDocument> workingDocuments = loadRequestedWorkingDocumentsPort.loadByUserWithExcludeStatus(
			user, pageable, DocStatusType.REVIEW, status);
		List<WorkingDocumentWithReviewDto> content = workingDocuments.getContent()
			.stream()
			.map((WorkingDocument workingDocument) -> WorkingDocumentWithReviewDto.toDto(workingDocument, null))
			.toList();

		return new PageImpl<>(content, pageable, workingDocuments.getTotalElements());
	}
}
