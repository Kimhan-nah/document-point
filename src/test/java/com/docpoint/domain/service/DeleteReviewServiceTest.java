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

import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

@ExtendWith(MockitoExtension.class)
@DisplayName("리뷰 삭제 테스트")
class DeleteReviewServiceTest {
	@InjectMocks
	private DeleteReviewService deleteReviewService;

	@Mock
	private SaveReviewPort saveReviewPort;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Mock
	private LoadDocumentReviewerPort loadDocumentReviewerPort;

	@Test
	@DisplayName("working document의 리뷰어가 등록한 리뷰가 있을 경우, Port의 delete 함수를 호출하고 삭제를 성공한다.")
	void 리뷰_삭제_성공() {
		// given
		WorkingDocument workingDocument = mock(WorkingDocument.class);
		User user = mock(User.class);
		DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)).willReturn(
			Optional.of(documentReviewer));
		given(loadReviewPort.existsReviewByReviewer(workingDocument, user)).willReturn(true);

		// when
		deleteReviewService.deleteReview(workingDocument, user);

		// then
		verify(saveReviewPort, times(1)).delete(documentReviewer);
	}

	@Nested
	@DisplayName("리뷰 삭제 실패")
	class DeleteReviewFail {
		@Test
		@DisplayName("리뷰어가 아닌 경우, ForbiddenException이 발생한다.")
		void 리뷰어가_아닌_경우() {
			// given
			WorkingDocument workingDocument = mock(WorkingDocument.class);
			User user = mock(User.class);
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)).willReturn(
				Optional.empty());

			// when, then
			assertThatThrownBy(() -> deleteReviewService.deleteReview(workingDocument, user))
				.isInstanceOf(ForbiddenException.class);
		}

		@Test
		@DisplayName("리뷰가 존재하지 않는 경우, NotFoundException이 발생한다.")
		void 리뷰가_존재하지_않는_경우() {
			// given
			WorkingDocument workingDocument = mock(WorkingDocument.class);
			User user = mock(User.class);
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)).willReturn(
				Optional.of(new DocumentReviewer(null, workingDocument, user)));
			given(loadReviewPort.existsReviewByReviewer(workingDocument, user)).willReturn(false);

			// when, then
			assertThatThrownBy(() -> deleteReviewService.deleteReview(workingDocument, user))
				.isInstanceOf(NotFoundException.class);
		}
	}
}
