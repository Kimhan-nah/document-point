package com.docpoint.application.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.WorkingDocument;

public interface LoadWorkingDocumentsPort {
	Page<WorkingDocument> loadByTeamId(long teamId, Pageable pageable);

	Optional<WorkingDocument> loadById(long workingDocumentId);
}
