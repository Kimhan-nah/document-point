package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadReviewsOfWorkingDocumentPort;
import com.docpoint.domain.model.Review;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument의 리뷰 전체 조회 서비스 테스트")
class GetReviewsServiceTest {
	@InjectMocks
	private GetReviewsService getReviewsService;

	@Mock
	private LoadReviewsOfWorkingDocumentPort loadReviewsOfWorkingDocumentPort;

	@Test
	@DisplayName("유저의 권한이 팀 멤버이고 WorkingDocument 담당자인 경우, WorkingDocument의 리뷰 전체 조회 성공")
	void getReviewsOfWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		given(loadReviewsOfWorkingDocumentPort.loadByWorkingDocumentId(workingDocumentId)).willReturn(List.of());

		// when
		List<Review> reviews = getReviewsService.getReviewsOfWorkingDocument(workingDocumentId);

		// then
		assertThat(reviews).isEmpty();
	}
}
