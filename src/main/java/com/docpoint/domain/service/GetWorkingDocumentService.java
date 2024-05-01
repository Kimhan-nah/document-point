package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetWorkingDocumentService implements GetWorkingDocumentUseCase {
	private final LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	/**
	 * @param workingDocumentId 조회할 workingDocument ID
	 * @return 조회된 workingDocument
	 * @throws NotFoundException WorkingDocument가 존재하지 않을 경우
	 * @throws NotFoundException 삭제된 WorkingDocument일 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public WorkingDocument getWorkingDocument(long workingDocumentId) {
		WorkingDocument workingDocument = loadWorkingDocumentsPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT));
		if (workingDocument.isDeleted()) {
			throw new NotFoundException(ErrorType.DELETED_WORKING_DOCUMENT);
		}
		return workingDocument;
	}
}
