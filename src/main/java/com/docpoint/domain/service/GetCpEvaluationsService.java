package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetCpEvaluationsUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetCpEvaluationsService implements GetCpEvaluationsUseCase {
	private final LoadCpEvaluationsPort loadCpEvaluationsPort;

	/**
	 * 최종 기여도 조회
	 * @param user 조회 요청한 유저
	 * @param workingDocument 조회할 WorkingDocument ID
	 * @return 최종 기여도
	 * @throws ForbiddenException user가 TEAM_MEMBER이고 working 담당자 본인이 아닌 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CpEvaluation> getCpEvaluations(User user, WorkingDocument workingDocument) {
		if (!workingDocument.getWorking().getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_CP_ACCESS);
		}
		return loadCpEvaluationsPort.loadByWorkingDocument(workingDocument);
	}
}
