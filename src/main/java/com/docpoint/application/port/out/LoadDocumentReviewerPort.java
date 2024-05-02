package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface LoadDocumentReviewerPort {
	List<DocumentReviewer> loadByWorkingDocumentId(long workingDocumentId);

	Optional<DocumentReviewer> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user);
}
