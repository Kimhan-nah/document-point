package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.RegisterCpEvaluationUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.application.port.out.LoadDocumentReviewersPort;
import com.docpoint.application.port.out.SaveCpEvaluationPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterCpEvaluationService implements RegisterCpEvaluationUseCase {
	private final LoadDocumentReviewersPort loadDocumentReviewersPort;
	private final SaveCpEvaluationPort saveCpEvaluationPort;
	private final LoadCpEvaluationsPort loadCpEvaluationsPort;

	@Override
	@Transactional // TODO 동시성 고려하기?
	public void registerCpEvaluation(CpEvaluation cpEvaluation, WorkingDocument workingDocument, User reviewer) {
		loadDocumentReviewersPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
		if (workingDocument.getStatus() == DocStatusType.APPROVED) {
			throw new ConflictException(ErrorType.CONFLICT_DOCUMENT_STATUS);
		}
		if (loadCpEvaluationsPort.loadByWorkingDocumentAndUser(workingDocument, reviewer).isPresent()) {
			throw new ConflictException(ErrorType.CONFLICT_DOCUMENT_STATUS);
		}
		saveCpEvaluationPort.save(cpEvaluation);
	}
}
