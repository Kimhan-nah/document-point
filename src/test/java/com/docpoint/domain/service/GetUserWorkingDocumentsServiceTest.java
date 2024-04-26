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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadUserPort;
import com.docpoint.application.port.out.LoadUserWorkingDocumentsPort;
import com.docpoint.common.exception.CustomRuntimeException;
import com.docpoint.domain.model.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("유저의 워킹 문서(WorkingDocument) 조회")
class GetUserWorkingDocumentsServiceTest {
	@InjectMocks
	private GetUserWorkingDocumentsService getUserWorkingDocumentsService;

	@Mock
	private LoadUserPort loadUserPort;

	@Mock
	private LoadUserWorkingDocumentsPort loadUserWorkingDocumentsPort;

	@Test
	@DisplayName("유저의 워킹 문서(WorkingDocument)를 조회한다")
	void 조회_성공() {
		// given
		long userId = 1L;
		Pageable pageable = mock(Pageable.class);
		given(loadUserPort.loadById(userId)).willReturn(Optional.of(mock(User.class)));
		given(loadUserWorkingDocumentsPort.loadByUserId(userId, pageable)).willReturn(Page.empty());

		// when
		getUserWorkingDocumentsService.getUserWorkingDocuments(userId, pageable);

		// then
		verify(loadUserPort, times(1)).loadById(userId);
	}

	@Nested
	@DisplayName("유저의 워킹 문서(WorkingDocument) 조회 실패")
	class Failure {
		@Test
		@DisplayName("존재하지 않는 유저의 경우, CustomRuntimeException을 발생시킨다.")
		void 존재하지_않는_유저_실패() {
			// given
			long notFoundUserId = 1L;
			given(loadUserPort.loadById(notFoundUserId)).willReturn(Optional.empty());

			// when, then
			assertThatThrownBy(
				() -> getUserWorkingDocumentsService.getUserWorkingDocuments(notFoundUserId, mock(Pageable.class)))
				.isInstanceOf(CustomRuntimeException.class);
		}
	}
}
