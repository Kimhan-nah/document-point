package com.docpoint.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.RegisterCpEvaluationUseCase;
import com.docpoint.application.port.in.UpdateWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationPort;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.SaveCpEvaluationPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.RoleType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class RegisterCpEvaluationService implements RegisterCpEvaluationUseCase {
	private final LoadDocumentReviewerPort loadDocumentReviewerPort;
	private final SaveCpEvaluationPort saveCpEvaluationPort;
	private final LoadCpEvaluationPort loadCpEvaluationPort;
	private final UpdateWorkingDocumentUseCase updateWorkingDocumentUseCase;

	/**
	 * CP 평가 등록
	 * @param cpEvaluation 등록할 CP 평가
	 * @param workingDocument CP 평가한 working document
	 * @param reviewer CP 평가한 평가자
	 * @return 등록된 CP 평가
	 */
	@Override
	@Transactional
	public CpEvaluation registerCpEvaluation(CpEvaluation cpEvaluation, WorkingDocument workingDocument,
		User reviewer) {
		DocumentReviewer documentReviewer = getDocumentReviewer(workingDocument, reviewer);
		checkDocumentStatus(workingDocument);
		checkCpEvaluation(workingDocument, reviewer);
		updateWorkingDocumentStatus(workingDocument, reviewer);
		cpEvaluation = cpEvaluation.updateDocumentReviewer(documentReviewer);
		return saveCpEvaluationPort.save(cpEvaluation);
	}

	private DocumentReviewer getDocumentReviewer(WorkingDocument workingDocument, User reviewer) {
		return loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
	}

	private void checkDocumentStatus(WorkingDocument workingDocument) {
		if (workingDocument.getStatus() == DocStatusType.APPROVED) {
			throw new ConflictException(ErrorType.CONFLICT_DOCUMENT_STATUS);
		}
	}

	private void checkCpEvaluation(WorkingDocument workingDocument, User reviewer) {
		loadCpEvaluationPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
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
