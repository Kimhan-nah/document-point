package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetWorkingDocumentService implements GetWorkingDocumentUseCase {
	private final LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	/**
	 * @param workingDocumentId 조회할 workingDocument ID
	 * @return 조회된 workingDocument
	 */
	@Override
	@Transactional(readOnly = true)
	public WorkingDocument getWorkingDocument(long workingDocumentId) {
		return loadWorkingDocumentsPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException("WorkingDocument가 존재하지 않습니다. ID: " + workingDocumentId));
	}
}
