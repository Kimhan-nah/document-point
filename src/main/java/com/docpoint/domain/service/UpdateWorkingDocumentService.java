package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.UpdateWorkingDocumentUseCase;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UpdateWorkingDocumentService implements UpdateWorkingDocumentUseCase {
	private final SaveWorkingDocumentPort saveWorkingDocumentPort;

	@Override
	@Transactional
	public WorkingDocument updateStatus(WorkingDocument workingDocument, DocStatusType docStatusType) {
		WorkingDocument updatedWorkingDocument = WorkingDocument.builder()
			.id(workingDocument.getId())
			.working(workingDocument.getWorking())
			.title(workingDocument.getTitle())
			.content(workingDocument.getContent())
			.status(docStatusType)
			.docType(workingDocument.getDocType())
			.link(workingDocument.getLink())
			.isDeleted(workingDocument.isDeleted())
			.build();
		return saveWorkingDocumentPort.save(updatedWorkingDocument);
	}
}
