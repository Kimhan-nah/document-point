package com.docpoint.domain.service;

import java.util.List;
import java.util.Map;

import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.common.exception.ErrorCode;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;
import com.docpoint.domain.type.WorkingStatusType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterWorkingDocumentService implements RegisterWorkingDocumentUseCase {
	private static final int MIN_URL_COUNT = 1;
	private final SaveWorkingDocumentPort saveWorkingDocumentPort;

	@Override
	public void registerWorkingDocument(WorkingDocument workingDocument) {
		Working working = workingDocument.getWorking();
		checkWorkingIsDeleted(working);
		checkWorkingStatus(working.getStatus());
		checkLinks(workingDocument.getLinks());
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

	private void checkLinks(Map<DocType, List<String>> links) {
		int urlCount = 0;

		for (Map.Entry<DocType, List<String>> entry : links.entrySet()) {
			if (entry.getValue() == null) {
				throw new CustomRuntimeException(ErrorCode.NULL_POINT, "links는 null이 될 수 없습니다.");
			}
			urlCount += entry.getValue().size();
		}

		if (urlCount < MIN_URL_COUNT) {
			throw new CustomRuntimeException(ErrorCode.BAD_REQUEST, "links의 url 갯수가 부족하여 문서를 생성할 수 없습니다.");
		}
	}

	private void checkWorkingDocumentStatus(DocStatusType status) {
		if (status != DocStatusType.REVIEW) {
			throw new CustomRuntimeException(ErrorCode.BAD_REQUEST, "문서 상태가 잘못되었습니다.");
		}
	}

}
