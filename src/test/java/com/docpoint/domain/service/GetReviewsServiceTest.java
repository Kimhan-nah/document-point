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
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument의 리뷰 조회 서비스 테스트")
class GetReviewsServiceTest {
	@InjectMocks
	private GetReviewsOfWorkingDocumentService getReviewsService;

	@Mock
	private LoadReviewsOfWorkingDocumentPort loadReviewsOfWorkingDocumentPort;

	@Mock
	private LoadWorkingDocumentsPort loadWorkingDocumentPort;

	@Test
	@DisplayName("WorkingDocument의 리뷰 전체 조회 성공")
	void getReviewsOfWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		given(loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentId(workingDocumentId)).willReturn(List.of());
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.of(workingDocument));

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
		given(loadReviewsOfWorkingDocumentPort.loadByReviewId(reviewId)).willReturn(Optional.of(mock(Review.class)));
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.of(workingDocument));

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
		given(loadReviewsOfWorkingDocumentPort.loadByReviewId(reviewId)).willReturn(Optional.empty());
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.of(workingDocument));

		// when, then
		assertThatThrownBy(() -> getReviewsService.getReview(workingDocumentId, reviewId))
			.isInstanceOf(NotFoundException.class);
	}

	@Test
	@DisplayName("WorkingDocument가 없는 경우, Not Found Exception 발생")
	void getReviewOfWorkingDocumentNotFoundWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		long reviewId = 1L;
		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.empty());

		// when, then
		assertThatThrownBy(() -> getReviewsService.getReview(workingDocumentId, reviewId))
			.isInstanceOf(NotFoundException.class);
	}

	@Test
	@DisplayName("삭제된 WorkingDocument의 리뷰 조회 시, Not Found Exception 발생")
	void getReviewOfWorkingDocumentDeletedWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		long reviewId = 1L;
		WorkingDocument deletedWorkingDocument = WorkingDocumentTestData.createDeletedWorkingDocument();
		given(loadWorkingDocumentPort.loadById(workingDocumentId)).willReturn(Optional.of(deletedWorkingDocument));

		// when, then
		assertThatThrownBy(() -> getReviewsService.getReview(workingDocumentId, reviewId))
			.isInstanceOf(NotFoundException.class);
	}
}
