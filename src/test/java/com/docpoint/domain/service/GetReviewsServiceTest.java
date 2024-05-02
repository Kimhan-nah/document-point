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

import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument의 리뷰 조회 서비스 테스트")
class GetReviewsServiceTest {
	@InjectMocks
	private GetReviewsService getReviewsService;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Test
	@DisplayName("WorkingDocument의 리뷰 전체 조회 성공")
	void getReviewsOfWorkingDocument() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		given(loadReviewPort.loadByWorkingDocument(workingDocument)).willReturn(List.of());

		// when
		List<Review> reviews = getReviewsService.getAllReviews(workingDocument);

		// then
		assertThat(reviews).isEmpty();
	}

	@Test
	@DisplayName("리뷰 조회 성공")
	void getReviewOfWorkingDocument() {
		// given
		long reviewId = 1L;
		given(loadReviewPort.load(reviewId)).willReturn(Optional.of(mock(Review.class)));
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();

		// when
		Review review = getReviewsService.getReview(reviewId);

		// then
		assertThat(review).isNotNull();
	}

	@Test
	@DisplayName("리뷰가 없는 경우, Not Found Exception 발생")
	void getReviewOfWorkingDocumentNotFound() {
		// given
		long reviewId = 1L;
		given(loadReviewPort.load(reviewId)).willReturn(Optional.empty());
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();

		// when, then
		assertThatThrownBy(() -> getReviewsService.getReview(reviewId))
			.isInstanceOf(NotFoundException.class);
	}
}
