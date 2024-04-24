package com.docpoint.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.WorkingDocument;

public interface LoadWorkingDocumentsPort {
	List<WorkingDocument> loadWorkingDocumentsByTeamId(Long teamId, Pageable pageable);
}
