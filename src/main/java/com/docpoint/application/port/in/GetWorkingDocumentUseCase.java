package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface GetWorkingDocumentUseCase {
	WorkingDocument getWorkingDocument(long workingDocumentId);

	List<User> getDocumentReviewers(WorkingDocument workingDocument);
}
