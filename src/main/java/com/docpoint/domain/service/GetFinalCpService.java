package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetFinalCpUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetFinalCpService implements GetFinalCpUseCase {
	private final LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	private final LoadCpEvaluationsPort loadCpEvaluationsPort;

	/**
	 * 최종 기여도 조회
	 * @param user 조회 요청한 유저
	 * @param workingDocumentId 조회할 WorkingDocument ID
	 * @return 최종 기여도
	 * @throws NotFoundException WorkingDocument가 존재하지 않거나 삭제된 경우
	 * @throws ForbiddenException user가 TEAM_MEMBER이고 working 담당자 본인이 아닌 경우
	 * @throws NotFoundException 최종 기여도가 존재하지 않는 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public CpEvaluation getFinalCp(User user, long workingDocumentId) {
		WorkingDocument workingDocument = loadWorkingDocumentsPort.loadById(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT));
		if (workingDocument.isDeleted()) {
			throw new NotFoundException(ErrorType.NOT_FOUND_WORKING_DOCUMENT);
		}
		if (!workingDocument.getWorking().getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_CP_ACCESS);
		}
		return loadCpEvaluationsPort.loadFinalCpByWorkingDocumentId(workingDocumentId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_CP));
	}
}
