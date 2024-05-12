package com.docpoint.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

public interface GetAllWorkingDocumentsUseCase {
	Page<WorkingDocument> getAllWorkingDocumentsByTeamId(long teamId, Pageable pageable, DocStatusType status);
}
