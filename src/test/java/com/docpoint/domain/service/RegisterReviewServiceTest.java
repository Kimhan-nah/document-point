package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.util.ReviewTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("review 등록 테스트")
class RegisterReviewServiceTest {
	@InjectMocks
	private RegisterReviewService registerReviewService;

	@Mock
	private LoadDocumentReviewerPort loadDocumentReviewerPort;

	@Mock
	private SaveReviewPort saveReviewPort;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Test
	@DisplayName("review 등록 성공")
	void registerReview() {
		// given
		WorkingDocument workingDocument = mock(WorkingDocument.class);
		DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
		User reviewer = mock(User.class);
		List<Evaluation> reviews = ReviewTestData.createReviewsWithoutReviewer();
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
			.willReturn(Optional.of(documentReviewer));
		given(loadReviewPort.existsReviewByReviewer(workingDocument, reviewer)).willReturn(false);

		// when
		registerReviewService.registerReview(reviews, reviewer, workingDocument);

		// then
		verify(saveReviewPort, times(reviews.size())).save(any(Review.class));
	}

	@Test
	@DisplayName("리뷰 수정 성공")
	void 리뷰_수정_성공() {
		// given
		WorkingDocument workingDocument = mock(WorkingDocument.class);
		DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
		User reviewer = mock(User.class);
		List<Evaluation> reviews = ReviewTestData.createReviewsWithoutReviewer();
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
			.willReturn(Optional.of(documentReviewer));

		// when
		registerReviewService.updateReview(reviews, reviewer, workingDocument);

		// then
		verify(saveReviewPort, times(reviews.size())).save(any(Review.class));
	}

	@Nested
	@DisplayName("review 등록 실패")
	class RegisterReviewFail {
		@Test
		@DisplayName("지정된 리뷰어가 아닌 경우, ForbiddenException 발생")
		void registerReviewFailWhenReviewIsNull() {
			// given
			WorkingDocument workingDocument = mock(WorkingDocument.class);
			DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
			User reviewer = mock(User.class);
			List<Evaluation> reviews = List.of(mock(Evaluation.class));
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
				.willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(
				() -> registerReviewService.registerReview(reviews, reviewer, workingDocument))
				.isInstanceOf(ForbiddenException.class)
				.hasMessage(ErrorType.FORBIDDEN_REVIEWER.getMessage());
		}

		@Test
		@DisplayName("이미 리뷰를 등록한 경우, ConflictException 발생")
		void 이미_리뷰_존재() {
			// given
			WorkingDocument workingDocument = mock(WorkingDocument.class);
			DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
			User reviewer = mock(User.class);
			List<Evaluation> reviews = ReviewTestData.createReviewsWithoutReviewer();
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
				.willReturn(Optional.of(documentReviewer));
			given(loadReviewPort.existsReviewByReviewer(workingDocument, reviewer)).willReturn(true);

			// when, then
			assertThatThrownBy(
				() -> registerReviewService.registerReview(reviews, reviewer, workingDocument))
				.isInstanceOf(ConflictException.class)
				.hasMessage(ErrorType.CONFLICT_REVIEW.getMessage());
		}
	}

	@Nested
	@DisplayName("리뷰 수정 실패")
	class 리뷰_수정_실패 {
		@Test
		@DisplayName("지정된 리뷰어가 아닌 경우, ForbiddenException 발생")
		void 리뷰어가_아닌_경우() {
			// given
			WorkingDocument workingDocument = mock(WorkingDocument.class);
			DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
			User reviewer = mock(User.class);
			List<Evaluation> reviews = List.of(mock(Evaluation.class));
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
				.willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(
				() -> registerReviewService.updateReview(reviews, reviewer, workingDocument))
				.isInstanceOf(ForbiddenException.class)
				.hasMessage(ErrorType.FORBIDDEN_REVIEWER.getMessage());
		}
	}
}
