package com.docpoint.application.port.in;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.WorkingDocument;

public interface GetUserWorkingDocumentsUseCase {
	List<WorkingDocument> getUserWorkingDocuments(long userId, Pageable pageable);
}
