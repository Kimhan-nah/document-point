package com.docpoint.domain.service;

import com.docpoint.application.port.in.DeleteWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteWorkingDocumentService implements DeleteWorkingDocumentUseCase {
	private final SaveWorkingDocumentPort saveWorkingDocumentPort;
	private final LoadReviewPort loadReviewPort;

	/**
	 * 작업문서(WorkingDocument) 삭제
	 * @param workingDocument 삭제할 작업문서
	 * @param user 작업문서를 삭제할 사용자
	 * @throws ForbiddenException 작업문서의 담당자가 아닌 경우
	 * @throws ConflictException 작업문서의 상태가 REVIEW가 아닌 경우
	 * @throws ConflictException 리뷰가 존재할 경우 삭제 불가능
	 */
	@Override
	public void deleteWorkingDocument(WorkingDocument workingDocument, User user) {
		if (!workingDocument.getWorking().getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_ASSIGNEE);
		}
		if (workingDocument.getStatus() != DocStatusType.REVIEW) {
			throw new ConflictException(ErrorType.CONFLICT_DOCUMENT_STATUS);
		}
		if (loadReviewPort.existsReview(workingDocument)) {
			throw new ConflictException(ErrorType.CONFLICT_REVIEW);
		}
		workingDocument.delete();
		saveWorkingDocumentPort.update(workingDocument);
	}
}
