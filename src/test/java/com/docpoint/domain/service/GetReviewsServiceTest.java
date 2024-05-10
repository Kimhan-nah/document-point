package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument의 리뷰 조회 서비스 테스트")
class GetReviewsServiceTest {
	@InjectMocks
	private GetReviewsService getReviewsService;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Mock
	private LoadDocumentReviewerPort loadDocumentReviewerPort;

	@Test
	@DisplayName("WorkingDocument의 리뷰 전체 조회 성공")
	void getReviewsOfWorkingDocument() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User assignee = workingDocument.getWorking().getAssignee();
		given(loadReviewPort.loadByWorkingDocument(workingDocument)).willReturn(List.of());

		// when
		List<Review> reviews = getReviewsService.getAllReviews(workingDocument, assignee);

		// then
		assertThat(reviews).isEmpty();
	}

	@Test
	@DisplayName("리뷰 조회 성공")
	void getReviewOfWorkingDocument() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User reviewer = mock(User.class);
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
			.willReturn(Optional.of(mock(DocumentReviewer.class)));
		given(loadReviewPort.loadUserReviewOfDocument(any())).willReturn(List.of());

		// when
		List<Review> review = getReviewsService.getReview(workingDocument, reviewer);

		// then
		assertThat(review).isNotNull();
	}

	@Test
	@DisplayName("리뷰어가 아닌 경우, ForbiddenException 발생")
	void getReviewOfWorkingDocumentWithForbiddenException() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User user = mock(User.class);
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, user)).willReturn(
			Optional.empty());

		// when
		assertThatThrownBy(() -> getReviewsService.getReview(workingDocument, user))
			.isInstanceOf(ForbiddenException.class);
	}
}
