package com.docpoint.application.port.in;

import com.docpoint.domain.entity.WorkingDocument;

public interface GetWorkingDocumentUseCase {
	WorkingDocument getWorkingDocument(long workingDocumentId);
}
