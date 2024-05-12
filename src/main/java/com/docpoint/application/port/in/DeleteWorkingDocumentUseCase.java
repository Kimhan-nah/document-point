package com.docpoint.application.port.in;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface DeleteWorkingDocumentUseCase {
	void deleteWorkingDocument(WorkingDocument workingDocument, User user);
}
