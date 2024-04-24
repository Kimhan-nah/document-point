package com.docpoint.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.WorkingDocument;

public interface LoadWorkingDocumentsPort {
	List<WorkingDocument> loadByTeamId(Long teamId, Pageable pageable);
}
