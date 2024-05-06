package com.docpoint.application.port.out;

import com.docpoint.domain.entity.DocumentReviewer;

public interface SaveDocumentReviewerPort {
	public DocumentReviewer save(DocumentReviewer documentReviewer);
}
