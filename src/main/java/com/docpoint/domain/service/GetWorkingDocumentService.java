package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadWorkingDocumentPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetWorkingDocumentService implements GetWorkingDocumentUseCase {
	private final LoadWorkingDocumentPort loadWorkingDocumentPort;

	/**
	 * @param workingDocumentId 조회할 workingDocument ID
	 * @return 조회된 workingDocument
	 * @throws NotFoundException WorkingDocument가 존재하지 않을 경우
	 * @throws NotFoundException 삭제된 WorkingDocument일 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public WorkingDocument getWorkingDocument(long workingDocumentId) {
		WorkingDocument workingDocument = loadWorkingDocumentPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT));
		if (workingDocument.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_WORKING_DOCUMENT);
		}
		return workingDocument;
	}

	/**
	 * DocumentReviewer 목록 조회 - TEAM_LEADER는 제외
	 * @param workingDocument 조회할 workingDocument
	 * @return 조회된 DocumentReviewer 목록
	 */
	@Override
	@Transactional(readOnly = true)
	public List<User> getDocumentReviewers(WorkingDocument workingDocument) {
		return loadWorkingDocumentPort.loadReviewers(workingDocument)
			.stream()
			.filter(user -> user.getRole() != RoleType.TEAM_LEADER)
			.toList();
	}
}
