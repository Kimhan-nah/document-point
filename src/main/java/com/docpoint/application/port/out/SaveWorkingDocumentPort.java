package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.WorkingDocument;

public interface SaveWorkingDocumentPort {
	void save(WorkingDocument workingDocument, List<DocumentReviewer> documentReviewers);

	void update(WorkingDocument workingDocument);
}
