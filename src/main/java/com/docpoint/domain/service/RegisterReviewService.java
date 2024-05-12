package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.RegisterReviewUseCase;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterReviewService implements RegisterReviewUseCase {
	private final LoadDocumentReviewerPort loadDocumentReviewerPort;
	private final LoadReviewPort loadReviewPort;

	private final SaveReviewPort saveReviewPort;

	/**
	 * review 등록
	 * @param review 등록할 review 내용
	 * @param reviewer review를 등록할 사용자
	 * @param workingDocument review를 등록할 workingDocument
	 * @throws ForbiddenException 지정된 reivewer가 아닌 경우
	 * @throws ConflictException 이미 review를 등록한 경우
	 */
	@Override
	@Transactional
	public void registerReview(List<Evaluation> review, User reviewer, WorkingDocument workingDocument) {
		DocumentReviewer documentReviewer = loadDocumentReviewerPort.loadByWorkingDocumentAndUser(
				workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
		if (loadReviewPort.existsReviewByReviewer(workingDocument, reviewer)) {
			throw new ConflictException(ErrorType.CONFLICT_REVIEW);
		}
		for (Evaluation evaluation : review) {
			saveReviewPort.save(
				new Review(null, documentReviewer, evaluation.getQuestion(), evaluation.getAnswer(), false));
		}
	}

	/**
	 * review 수정
	 * @param review 수정할 review 내용
	 * @param reviewer review를 수정할 사용자
	 * @param workingDocument review를 수정할 workingDocument
	 * @throws ForbiddenException 지정된 reivewer가 아닌 경우
	 */
	@Override
	@Transactional
	public void updateReview(List<Evaluation> review, User reviewer, WorkingDocument workingDocument) {
		DocumentReviewer documentReviewer = loadDocumentReviewerPort.loadByWorkingDocumentAndUser(
				workingDocument, reviewer)
			.orElseThrow(() -> new ForbiddenException(ErrorType.FORBIDDEN_REVIEWER));
		for (Evaluation evaluation : review) {
			saveReviewPort.save(
				new Review(null, documentReviewer, evaluation.getQuestion(), evaluation.getAnswer(), false));
		}
	}
}
