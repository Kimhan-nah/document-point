package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.util.ReviewTestData;
import com.docpoint.util.UserTestData;
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
	@DisplayName("WorkingDocument의 리뷰 전체 조회 1개 성공")
	void getReviewsOfWorkingDocument() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User assignee = workingDocument.getWorking().getAssignee();
		DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
		Review review = ReviewTestData.createReview();
		given(loadDocumentReviewerPort.loadByWorkingDocumentId(workingDocument.getId()))
			.willReturn(List.of(documentReviewer));
		given(loadReviewPort.loadUserReviewOfDocument(documentReviewer))
			.willReturn(List.of(review));

		// when
		Map<User, List<Evaluation>> allReviews = getReviewsService.getAllReviews(workingDocument, assignee);

		// then
		assertThat(allReviews.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Working의 담당자가 아닌 팀 멤버가 리뷰 전체 조회할 경우, ForbiddenException 발생")
	void 담당자_아닌_팀멤버_리뷰_전체_조회() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User notAssigneeMember = UserTestData.createTeamMember(mock(Team.class));

		// when, then
		assertThatThrownBy(() -> getReviewsService.getAllReviews(workingDocument, notAssigneeMember))
			.isInstanceOf(ForbiddenException.class)
			.hasMessage(ErrorType.FORBIDDEN_ASSIGNEE.getMessage());
	}

	@Test
	@DisplayName("리뷰 조회 성공 - 리뷰가 없을 경우, NotFoundException 발생")
	void getReviewOfWorkingDocument() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User reviewer = mock(User.class);
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
			.willReturn(Optional.of(mock(DocumentReviewer.class)));
		given(loadReviewPort.loadUserReviewOfDocument(any())).willReturn(List.of());

		// when, then
		assertThatThrownBy(() -> getReviewsService.getReview(workingDocument, reviewer))
			.isInstanceOf(NotFoundException.class)
			.hasMessage(ErrorType.NOT_FOUND_REVIEW.getMessage());
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
			.isInstanceOf(ForbiddenException.class)
			.hasMessage(ErrorType.FORBIDDEN_REVIEWER.getMessage());
	}

	@Test
	@DisplayName("리뷰 1개 조회 성공")
	void 리뷰_조회_성공() {
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User reviewer = mock(User.class);
		DocumentReviewer documentReviewer = mock(DocumentReviewer.class);
		Review review = mock(Review.class);
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
			.willReturn(Optional.of(documentReviewer));
		given(loadReviewPort.loadUserReviewOfDocument(documentReviewer)).willReturn(List.of(review));

		List<Evaluation> evaluations = getReviewsService.getReview(workingDocument, reviewer);

		assertThat(evaluations.size()).isEqualTo(1);
	}
}
