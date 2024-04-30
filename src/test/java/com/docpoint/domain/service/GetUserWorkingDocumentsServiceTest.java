package com.docpoint.domain.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadUserWorkingDocumentsPort;
import com.docpoint.domain.model.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("유저의 워킹 문서(WorkingDocument) 조회")
class GetUserWorkingDocumentsServiceTest {
	@InjectMocks
	private GetUserWorkingDocumentsService getUserWorkingDocumentsService;

	@Mock
	private LoadUserWorkingDocumentsPort loadUserWorkingDocumentsPort;

	@Test
	@DisplayName("유저의 워킹 문서(WorkingDocument)를 조회한다")
	void 조회_성공() {
		// given
		Pageable pageable = mock(Pageable.class);
		User user = mock(User.class);
		given(loadUserWorkingDocumentsPort.loadByUser(user, pageable)).willReturn(Page.empty());

		// when
		getUserWorkingDocumentsService.getUserWorkingDocuments(user, pageable);
	}
}
