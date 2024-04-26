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

import com.docpoint.application.port.out.LoadReviewsOfWorkingDocumentPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.Review;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument의 리뷰 조회 서비스 테스트")
class GetReviewsServiceTest {
	@InjectMocks
	private GetReviewsOfWorkingDocumentService getReviewsService;

	@Mock
	private LoadReviewsOfWorkingDocumentPort loadReviewsOfWorkingDocumentPort;

	@Test
	@DisplayName("WorkingDocument의 리뷰 전체 조회 성공")
	void getReviewsOfWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		given(loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentId(workingDocumentId)).willReturn(List.of());

		// when
		List<Review> reviews = getReviewsService.getAllReviews(workingDocumentId);

		// then
		assertThat(reviews).isEmpty();
	}

	@Test
	@DisplayName("리뷰 조회 성공")
	void getReviewOfWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		long reviewId = 1L;
		given(loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentIdAndReviewId(workingDocumentId, reviewId))
			.willReturn(Optional.of(mock(Review.class)));

		// when
		Review review = getReviewsService.getReview(workingDocumentId, reviewId);

		// then
		assertThat(review).isNotNull();
	}

	@Test
	@DisplayName("리뷰가 없는 경우, Not Found Exception 발생")
	void getReviewOfWorkingDocumentNotFound() {
		// given
		long workingDocumentId = 1L;
		long reviewId = 1L;
		given(loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentIdAndReviewId(workingDocumentId, reviewId))
			.willReturn(Optional.empty());

		// when, then
		assertThatThrownBy(() -> getReviewsService.getReview(workingDocumentId, reviewId))
			.isInstanceOf(NotFoundException.class);
	}
}
