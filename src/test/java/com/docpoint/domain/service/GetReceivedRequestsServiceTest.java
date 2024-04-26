package com.docpoint.domain.service;

import static org.mockito.BDDMockito.*;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadReceivedRequestsPort;
import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("요청 받은 WorkingDocument 목록 조회")
class GetReceivedRequestsServiceTest {
	@InjectMocks
	private GetReceivedRequestsService getReceivedRequestsService;

	@Mock
	private LoadReceivedRequestsPort loadReceivedRequestsPort;

	@Test
	@DisplayName("요청 받은 WorkingDocument 목록 조회 성공")
	void getReceivedRequests() {
		// given
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		Map<WorkingDocument, Optional<Review>> receivedRequests = Map.of(workingDocument, Optional.empty());
		Long userId = 1L;
		Pageable pageable = mock(Pageable.class);
		given(loadReceivedRequestsPort.loadByUserId(userId, pageable)).willReturn(receivedRequests);

		// when
		getReceivedRequestsService.getReceivedRequests(userId, pageable);

		// then
		verify(loadReceivedRequestsPort, times(1)).loadByUserId(userId, pageable);
	}
}
