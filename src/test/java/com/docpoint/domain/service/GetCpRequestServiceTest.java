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

import com.docpoint.application.port.out.LoadCpEvaluationsPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

@ExtendWith(MockitoExtension.class)
@DisplayName("승인 요청한 기여도(cp) 조회 테스트")
class GetCpRequestServiceTest {
	@InjectMocks
	private GetCpRequestService getFinalCpService;

	@Mock
	private LoadCpEvaluationsPort loadCpEvaluationsPort;

	@Test
	@DisplayName("승인 요청한 기여도(cp) 조회 성공")
	void 조회_성공() {
		given(loadCpEvaluationsPort.loadCpRequestByWorkingDocumentId(anyLong()))
			.willReturn(Optional.of(mock(CpEvaluation.class)));

		CpEvaluation cpRequest = getFinalCpService.getCpRequest(mock(User.class), mock(WorkingDocument.class));

		assertThat(cpRequest).isNotNull();
	}

	@Nested
	@DisplayName("승인 요청한 기여도(cp) 조회 실패")
	class 조회_실패 {
		@Test
		@DisplayName("요청 기여도(cp) 없는 경우, NotFoundException 발생")
		void 요청_기여도_없음() {
			// given
			given(loadCpEvaluationsPort.loadCpRequestByWorkingDocumentId(anyLong())).willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(() -> getFinalCpService.getCpRequest(mock(User.class), mock(WorkingDocument.class)))
				.isInstanceOf(NotFoundException.class);
		}
	}

}
