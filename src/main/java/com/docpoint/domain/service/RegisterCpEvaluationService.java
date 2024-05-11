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
	public void registerCpEvaluation(CpEvaluation cpEvaluation, WorkingDocument workingDocument,
		User reviewer) {
		DocumentReviewer documentReviewer = getDocumentReviewer(workingDocument, reviewer);
		checkDocumentStatus(workingDocument, reviewer);
		checkCpEvaluation(workingDocument, reviewer);
		updateWorkingDocumentStatus(workingDocument, reviewer);
		cpEvaluation = cpEvaluation.updateDocumentReviewer(documentReviewer);
		saveCpEvaluationPort.save(cpEvaluation);
	}

	/**
	 * @throws ForbiddenException CP 평가자가 아닌 경우
	 */
	private DocumentReviewer getDocumentReviewer(WorkingDocument workingDocument, User reviewer) {
		return loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
	}

	/**
	 * @throws ForbiddenException CP 평가가 불가능한 문서 상태일 경우
	 */
	private void checkDocumentStatus(WorkingDocument workingDocument, User reviewer) {
		if (reviewer.getRole() == RoleType.PART_LEADER && workingDocument.getStatus() != DocStatusType.REVIEW) {
			throw new ForbiddenException(ErrorType.CONFLICT_DOCUMENT_STATUS);
		}
	}

	/**
	 * @throws ForbiddenException 문서 리뷰 하지 않은 파트리더인 경우
	 * @throws ConflictException 이미 CP 평가한 경우
	 */
	private void checkCpEvaluation(WorkingDocument workingDocument, User reviewer) {
		if (reviewer.getRole() == RoleType.PART_LEADER) {
			// TODO 리뷰가 존재해야 CP 평가 가능
		}
		loadCpEvaluationPort.loadByWorkingDocumentAndUser(workingDocument, reviewer)
			.ifPresent(cpEvaluation -> {
				throw new ConflictException(ErrorType.CONFLICT_CP_EVALUATION);
			});
	}

	private void updateWorkingDocumentStatus(WorkingDocument workingDocument, User user) {
		if (user.getRole() == RoleType.TEAM_LEADER) {
			updateWorkingDocumentUseCase.updateStatus(workingDocument, DocStatusType.APPROVED);
		} else if (user.getRole() == RoleType.PART_LEADER) {
			updateWorkingDocumentUseCase.updateStatus(workingDocument, DocStatusType.APPROVAL_REQUEST);
		}
	}
}
