package com.docpoint.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.util.WorkingDocumentTestData;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkingDocument 조회 테스트")
class GetWorkingDocumentServiceTest {
	@InjectMocks
	private GetWorkingDocumentService getWorkingDocumentService;

	@Mock
	private LoadWorkingDocumentsPort loadWorkingDocumentsPort;

	@Test
	@DisplayName("WorkingDocument 조회 성공")
	void getWorkingDocument() {
		// given
		long workingDocumentId = 1L;
		WorkingDocument workingDocument = WorkingDocumentTestData.createWorkingDocument();
		given(loadWorkingDocumentsPort.loadById(workingDocumentId)).willReturn(Optional.of(workingDocument));

		// when
		getWorkingDocumentService.getWorkingDocument(workingDocumentId);

		// then
	}

	@Test
	@DisplayName("WorkingDocument가 존재하지 않을 경우, NotFoundException 발생")
	void getWorkingDocumentFail() {
		// given
		long workingDocumentId = 1L;

		// when, then
		assertThatThrownBy(() -> getWorkingDocumentService.getWorkingDocument(workingDocumentId))
			.isInstanceOf(NotFoundException.class);
	}

}
