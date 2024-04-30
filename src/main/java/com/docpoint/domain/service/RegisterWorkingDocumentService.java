package com.docpoint.domain.service;

import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.WorkingStatusType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class RegisterWorkingDocumentService implements RegisterWorkingDocumentUseCase {
	private final SaveWorkingDocumentPort saveWorkingDocumentPort;

	@Override
	public void registerWorkingDocument(WorkingDocument workingDocument) {
		Working working = workingDocument.getWorking();
		checkWorkingIsDeleted(working);
		checkWorkingStatus(working.getStatus());
		checkWorkingDocumentStatus(workingDocument.getStatus());

		saveWorkingDocumentPort.save(workingDocument);
	}

	private void checkWorkingIsDeleted(Working working) {
		if (working.isDeleted()) {
			throw new BadRequestException(ErrorType.DELETED_WORKING);
		}
	}

	private void checkWorkingStatus(WorkingStatusType status) {
		if (status == WorkingStatusType.WAITING) {
			throw new BadRequestException(ErrorType.BAD_WORKING_STATUS);
		}
	}

	private void checkWorkingDocumentStatus(DocStatusType status) {
		if (status != DocStatusType.REVIEW) {
			throw new BadRequestException(ErrorType.BAD_WORKING_DOCUMENT_STATUS);
		}
	}

}
