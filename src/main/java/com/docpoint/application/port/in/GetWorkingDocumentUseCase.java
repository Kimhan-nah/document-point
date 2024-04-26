package com.docpoint.application.port.in;

import com.docpoint.domain.model.WorkingDocument;

public interface GetWorkingDocumentUseCase {
	WorkingDocument getWorkingDocument(long workingDocumentId);
}
