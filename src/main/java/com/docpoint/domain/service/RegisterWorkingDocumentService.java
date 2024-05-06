package com.docpoint.domain.service;

import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
class RegisterWorkingDocumentService implements RegisterWorkingDocumentUseCase {
	private final SaveWorkingDocumentPort saveWorkingDocumentPort;

	/**
	 * 작업문서(WorkingDocument) 등록
	 * @param workingDocument 등록할 작업문서
	 * @param working 수행 작업
	 * @param user 작업문서를 등록할 사용자 (working의 assignee)
	 * @throws ForbiddenException working의 assignee가 아닌 경우
	 * @throws BadRequestException 작업문서의 상태가 REVIEW가 아닌 경우
	 */
	@Override
	public void registerWorkingDocument(WorkingDocument workingDocument, Working working, User user) {
		checkAssignee(working, user);
		checkWorkingDocumentStatus(workingDocument.getStatus());

		workingDocument = workingDocument.updateWorking(working);
		saveWorkingDocumentPort.save(workingDocument);
	}

	private void checkAssignee(Working working, User user) {
		if (!working.getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_ASSIGNEE);
		}
	}

	private void checkWorkingDocumentStatus(DocStatusType status) {
		if (status != DocStatusType.REVIEW) {
			throw new BadRequestException(ErrorType.BAD_WORKING_DOCUMENT_STATUS);
		}
	}

}
