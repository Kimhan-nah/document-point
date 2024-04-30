package com.docpoint.application.port.out;

import com.docpoint.domain.model.WorkingDocument;

public interface SaveWorkingDocumentPort {
	WorkingDocument save(WorkingDocument workingDocument);
}
