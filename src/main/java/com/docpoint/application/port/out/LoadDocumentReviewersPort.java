package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.DocumentReviewer;

public interface LoadDocumentReviewersPort {
	List<DocumentReviewer> loadByWorkingDocumentId(long workingDocumentId);

	Optional<DocumentReviewer> loadByWorkingDocumentIdAndUserId(long workingDocumentId, long userId);
}
