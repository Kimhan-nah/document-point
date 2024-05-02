package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.RegisterCpEvaluationUseCase;
import com.docpoint.application.port.in.UpdateWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.application.port.out.LoadDocumentReviewersPort;
import com.docpoint.application.port.out.SaveCpEvaluationPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RegisterCpEvaluationService implements RegisterCpEvaluationUseCase {
	private final LoadDocumentReviewersPort loadDocumentReviewersPort;
	private final SaveCpEvaluationPort saveCpEvaluationPort;
	private final LoadCpEvaluationsPort loadCpEvaluationsPort;
	private final UpdateWorkingDocumentUseCase updateWorkingDocumentUseCase;

	@Override
	@Transactional // TODO 동시성 고려하기?
	public CpEvaluation registerCpEvaluation(CpEvaluation cpEvaluation, WorkingDocument workingDocument,
		User reviewer) {
		checkReviewer(workingDocument, reviewer);
		checkDocumentStatus(workingDocument);
		checkCpEvaluation(workingDocument, reviewer);
		updateWorkingDocumentStatus(workingDocument, reviewer);
		return saveCpEvaluationPort.save(cpEvaluation);    // cascadeType.NONE이어야 함
	}

	private void checkReviewer(WorkingDocument workingDocument, User reviewer) {
		loadDocumentReviewersPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
	}

	private void checkDocumentStatus(WorkingDocument workingDocument) {
		if (workingDocument.getStatus() == DocStatusType.APPROVED) {
			throw new ConflictException(ErrorType.CONFLICT_DOCUMENT_STATUS);
		}
	}

	private void checkCpEvaluation(WorkingDocument workingDocument, User reviewer) {
		loadCpEvaluationsPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
			.ifPresent(cpEvaluation -> {
				throw new ConflictException(ErrorType.CONFLICT_CP_EVALUATION);
			});
	}

	private WorkingDocument updateWorkingDocumentStatus(WorkingDocument workingDocument, User user) {
		if (user.getRole() == RoleType.TEAM_LEADER) {
			return updateWorkingDocumentUseCase.updateStatus(workingDocument, DocStatusType.APPROVED);
		} else if (user.getRole() == RoleType.PART_LEADER) {
			return updateWorkingDocumentUseCase.updateStatus(workingDocument, DocStatusType.APPROVAL_REQUEST);
		}
		return null;
	}
}
