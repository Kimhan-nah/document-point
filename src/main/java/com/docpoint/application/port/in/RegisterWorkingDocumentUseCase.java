package com.docpoint.application.port.in;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;

public interface RegisterWorkingDocumentUseCase {
	void registerWorkingDocument(WorkingDocument workingDocument, Working working, User user);
}
