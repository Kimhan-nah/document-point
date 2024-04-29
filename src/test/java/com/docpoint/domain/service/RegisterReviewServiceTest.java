package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadDocumentReviewersPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.DocumentReviewer;
import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

@ExtendWith(MockitoExtension.class)
@DisplayName("review 등록 테스트")
class RegisterReviewServiceTest {
	@InjectMocks
	private RegisterReviewService registerReviewService;

	@Mock
	private LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	@Mock
	private LoadDocumentReviewersPort loadDocumentReviewersPort;

	@Mock
	private SaveReviewPort saveReviewPort;

	@Test
	@DisplayName("review 등록 성공")
	void registerReview() {
		// given
		WorkingDocument workingDocument = mock(WorkingDocument.class);
		DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
		User reviewer = mock(User.class);
		Review review = new Review(documentReviewer, false);
		given(loadWorkingDocumentsPort.loadById(anyLong())).willReturn(Optional.of(workingDocument));
		given(loadDocumentReviewersPort.loadByWorkingDocumentIdAndUserId(anyLong(), anyLong()))
			.willReturn(Optional.of(documentReviewer));

		// when
		registerReviewService.registerReview(review, reviewer, anyLong());

		// then
		verify(saveReviewPort, times(1)).save(any(Review.class));
	}

	@Nested
	@DisplayName("review 등록 실패")
	class RegisterReviewFail {
		@Test
		@DisplayName("working document가 존재하지 않는 경우, NotFoundException 발생")
		void registerReviewFailWhenReviewIdIsNull() {
			// given
			long workingDocumentId = 1L;
			given(loadWorkingDocumentsPort.loadById(workingDocumentId)).willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(
				() -> registerReviewService.registerReview(mock(Review.class), mock(User.class), workingDocumentId))
				.isInstanceOf(NotFoundException.class);
		}

		@Test
		@DisplayName("지정된 리뷰어가 아닌 경우, ForbiddenException 발생")
		void registerReviewFailWhenReviewIsNull() {
			// given
			WorkingDocument workingDocument = mock(WorkingDocument.class);
			DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
			User reviewer = mock(User.class);
			Review review = mock(Review.class);
			given(loadWorkingDocumentsPort.loadById(anyLong())).willReturn(Optional.of(workingDocument));
			given(loadDocumentReviewersPort.loadByWorkingDocumentIdAndUserId(anyLong(), anyLong()))
				.willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(
				() -> registerReviewService.registerReview(review, reviewer, anyLong()))
				.isInstanceOf(ForbiddenException.class);
		}

		// @Test
		// @DisplayName("삭제되지 않은 리뷰가 이미 존재하는 경우, ConflictException 발생")
		// void registerReviewFailWhenReviewIsAlreadyExist() {
		// 	// given
		// 	// when
		// 	// then
		// }
	}
}
