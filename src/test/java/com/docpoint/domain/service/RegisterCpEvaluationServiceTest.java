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

import com.docpoint.application.port.in.UpdateWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadCpEvaluationPort;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveCpEvaluationPort;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("기여도 입력(cp evaluation 등록) 서비스 테스트")
class RegisterCpEvaluationServiceTest {
	@InjectMocks
	private RegisterCpEvaluationService registerCpEvaluationService;

	@Mock
	private SaveCpEvaluationPort saveCpEvaluationPort;

	@Mock
	private LoadDocumentReviewerPort loadDocumentReviewerPort;

	@Mock
	private LoadCpEvaluationPort loadCpEvaluationPort;

	@Mock
	private UpdateWorkingDocumentUseCase updateWorkingDocumentUseCase;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Test
	@DisplayName("파트 리더인 경우, 승인 요청(APPROVAL_REQUEST) 상태로 변경하는 메서드를 호출한다.")
	void 기여도_입력_성공() {
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User partLeader = UserTestData.createPartLeader(new Team(null, "team", false));
		CpEvaluation cpEvaluation = CpEvaluation.builder().comment("comment").build();
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, partLeader))
			.willReturn(Optional.of(mock(DocumentReviewer.class)));
		given(loadCpEvaluationPort.loadByWorkingDocumentAndUser(workingDocument, partLeader))
			.willReturn(Optional.empty());
		given(loadReviewPort.existsReview(workingDocument, partLeader)).willReturn(true);

		// when
		registerCpEvaluationService.registerCpEvaluation(cpEvaluation, workingDocument, partLeader);

		// then
		verify(saveCpEvaluationPort, times(1)).save(any(CpEvaluation.class));
		verify(updateWorkingDocumentUseCase, times(1)).updateStatus(workingDocument, DocStatusType.APPROVAL_REQUEST);
	}

	@Test
	@DisplayName("팀 리더인 경우, 승인 완료(APPROVED) 상태로 변경하는 메서드를 호출한다.")
	void 팀_리더인_경우() {
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User teamLeader = UserTestData.createTeamLeader(new Team(null, "team", false));
		CpEvaluation cpEvaluation = CpEvaluation.builder().comment("comment").build();
		given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, teamLeader))
			.willReturn(Optional.of(mock(DocumentReviewer.class)));
		given(loadCpEvaluationPort.loadByWorkingDocumentAndUser(workingDocument, teamLeader))
			.willReturn(Optional.empty());

		// when
		registerCpEvaluationService.registerCpEvaluation(cpEvaluation, workingDocument, teamLeader);

		// then
		verify(saveCpEvaluationPort, times(1)).save(any(CpEvaluation.class));
		verify(updateWorkingDocumentUseCase, times(1)).updateStatus(workingDocument, DocStatusType.APPROVED);
	}

	@Nested
	@DisplayName("기여도 입력 실패")
	class 기여도_입력_실패 {
		@Test
		@DisplayName("리뷰어에 포함되지 않은 유저일 경우, ForbiddenException 발생")
		void 리뷰어가_아닌_경우() {
			WorkingDocument workingDocument = WorkingDocumentTestData.createDeletedWorkingDocument();
			User nonReviewer = mock(User.class);
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, nonReviewer))
				.willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(() -> registerCpEvaluationService.registerCpEvaluation(
				mock(CpEvaluation.class), workingDocument, nonReviewer))
				.isInstanceOf(ForbiddenException.class);

		}

		@Test
		@DisplayName("workingDocument의 상태가 REVIEW 상태가 아닌 '승인 완료(APPROVED)'일 경우, ConflictException 발생")
		void 승인_완료된_문서일_경우() {
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithStatus(
				DocStatusType.APPROVED);
			User reviewer = UserTestData.createPartLeader(new Team(null, "team", false));
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
				.willReturn(Optional.of(mock(DocumentReviewer.class)));

			// when, then
			assertThatThrownBy(() -> registerCpEvaluationService.registerCpEvaluation(
				mock(CpEvaluation.class), workingDocument, reviewer))
				.isInstanceOf(ConflictException.class);
		}

		@Test
		@DisplayName("이미 등록된 기여도(CpEvaluation)가 있을 경우, ConflictException 발생")
		void 이미_등록된_경우() {
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
			User reviewer = mock(User.class);
			given(loadDocumentReviewerPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
				.willReturn(Optional.of(mock(DocumentReviewer.class)));
			given(loadCpEvaluationPort.loadByWorkingDocumentAndUser(workingDocument, reviewer))
				.willReturn(Optional.of(mock(CpEvaluation.class)));

			// when, then
			assertThatThrownBy(() -> registerCpEvaluationService.registerCpEvaluation(
				mock(CpEvaluation.class), workingDocument, reviewer))
				.isInstanceOf(ConflictException.class);
		}
	}

}
