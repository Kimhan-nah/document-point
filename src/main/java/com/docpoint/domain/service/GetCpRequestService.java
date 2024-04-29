package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetCpRequestUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCpRequestService implements GetCpRequestUseCase {
	private final LoadCpEvaluationsPort loadCpEvaluationsPort;

	@Override
	@Transactional(readOnly = true)
	public CpEvaluation getCpRequest(User user, WorkingDocument workingDocument) {
		return loadCpEvaluationsPort.loadCpRequestByWorkingDocumentId(workingDocument.getId())
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_CP));
	}
}
