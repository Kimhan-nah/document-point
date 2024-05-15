package com.docpoint.domain.service;

import static com.docpoint.domain.type.DocStatusType.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("작업문서(WorkingDocuemnt) 삭제 테스트")
class DeleteWorkingDocumentServiceTest {
	@InjectMocks
	private DeleteWorkingDocumentService deleteWorkingDocumentService;

	@Mock
	private SaveWorkingDocumentPort saveWorkingDocumentPort;

	@Mock
	private LoadReviewPort loadReviewPort;

	@Test
	@DisplayName("WorkingDocument의 삭제 성공")
	void 삭제_성공() {
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		User assignee = workingDocument.getWorking().getAssignee();

		deleteWorkingDocumentService.deleteWorkingDocument(workingDocument, assignee);

		verify(saveWorkingDocumentPort, times(1)).update(workingDocument);
		assertThat(workingDocument.isDeleted()).isEqualTo(true);
	}

	@Nested
	@DisplayName("WorkingDocument 삭제 실패")
	class DeleteWorkingDocumentFail {
		@Test
		@DisplayName("WorkingDocument의 담당자가 아닌 경우, ForbiddenException이 발생한다.")
		void 담당자가_아닌_경우() {
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
			User notAssignee = mock(User.class);

			assertThatThrownBy(() -> deleteWorkingDocumentService.deleteWorkingDocument(workingDocument, notAssignee))
				.isInstanceOf(ForbiddenException.class)
				.hasMessage(ErrorType.FORBIDDEN_ASSIGNEE.getMessage());

		}

		@Test
		@DisplayName("WorkingDocument의 상태가 REVIEW가 아닌 경우, ConflictException이 발생한다.")
		void 상태가_REVIEW가_아닌_경우() {
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocumentWithStatus(APPROVAL_REQUEST);
			User assignee = workingDocument.getWorking().getAssignee();

			assertThatThrownBy(() -> deleteWorkingDocumentService.deleteWorkingDocument(workingDocument, assignee))
				.isInstanceOf(ConflictException.class)
				.hasMessage(ErrorType.CONFLICT_DOCUMENT_STATUS.getMessage());
		}

		@Test
		@DisplayName("WorkingDocument의 리뷰가 존재하는 경우, ConflictException이 발생한다.")
		void 리뷰가_존재하는_경우() {
			WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
			User assignee = workingDocument.getWorking().getAssignee();
			given(loadReviewPort.existsReview(workingDocument)).willReturn(true);

			assertThatThrownBy(() -> deleteWorkingDocumentService.deleteWorkingDocument(workingDocument, assignee))
				.isInstanceOf(ConflictException.class)
				.hasMessage(ErrorType.CONFLICT_REVIEW.getMessage());
		}
	}
}
