package com.docpoint.application.port.in;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.WorkingDocument;

public interface GetAllWorkingDocumentsUseCase {
	List<WorkingDocument> getAllWorkingDocumentsByTeamId(long teamId, Pageable pageable);
}