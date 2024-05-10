package com.docpoint.domain.service;

import static org.mockito.BDDMockito.*;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadReceivedRequestPort;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("요청 받은 WorkingDocument 목록 조회")
class GetReceivedRequestsServiceTest {
	@InjectMocks
	private GetReceivedRequestsService getReceivedRequestsService;

	@Mock
	private LoadReceivedRequestPort loadReceivedRequestPort;

	@Test
	@DisplayName("요청 받은 WorkingDocument 목록 조회 성공")
	@Disabled
	void getReceivedRequests() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		Map<WorkingDocument, Optional<Review>> receivedRequests = Map.of(workingDocument, Optional.empty());
		User user = mock(User.class);
		Pageable pageable = mock(Pageable.class);
		// given(loadReceivedRequestPort.loadByUser(user, pageable)).willReturn(receivedRequests);

		// when
		getReceivedRequestsService.getReceivedRequests(user, pageable, null);

		// then
		verify(loadReceivedRequestPort, times(1)).loadByUser(user, pageable, null);
	}
}
