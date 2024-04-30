package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.DocumentReviewer;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface LoadDocumentReviewersPort {
	List<DocumentReviewer> loadByWorkingDocumentId(long workingDocumentId);

	Optional<DocumentReviewer> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user);
}
