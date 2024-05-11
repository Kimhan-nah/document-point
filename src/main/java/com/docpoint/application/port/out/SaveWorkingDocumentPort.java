package com.docpoint.application.port.out;

import com.docpoint.domain.entity.WorkingDocument;

public interface SaveWorkingDocumentPort {
	void save(WorkingDocument workingDocument);
}
