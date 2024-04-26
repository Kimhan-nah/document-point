package com.docpoint.domain.service;

import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorCode;
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

		saveWorkingDocumentPort.saveWorkingDocument(workingDocument);
	}

	private void checkWorkingIsDeleted(Working working) {
		if (working.isDeleted()) {
			throw new CustomRuntimeException(ErrorCode.BAD_REQUEST, "삭제된 업무는 문서를 생성할 수 없습니다.");
		}
	}

	private void checkWorkingStatus(WorkingStatusType status) {
		if (status == WorkingStatusType.WAITING) {
			throw new CustomRuntimeException(ErrorCode.BAD_REQUEST, "대기 상태인 업무만 문서를 생성할 수 있습니다.");
		}
	}

	private void checkWorkingDocumentStatus(DocStatusType status) {
		if (status != DocStatusType.REVIEW) {
			throw new CustomRuntimeException(ErrorCode.BAD_REQUEST, "문서 상태가 잘못되었습니다.");
		}
	}

}
