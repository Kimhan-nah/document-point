package com.docpoint.application.port.out;

import com.docpoint.domain.entity.WorkingDocument;

public interface SaveWorkingDocumentPort {
	WorkingDocument save(WorkingDocument workingDocument);
}
