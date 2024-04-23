package com.docpoint.application.port.out;

import com.docpoint.domain.model.WorkingDocument;

public interface SaveWorkingDocumentPort {
	WorkingDocument saveWorkingDocument(WorkingDocument workingDocument);
}
