package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

public interface LoadWorkingDocumentPort {
	Page<WorkingDocument> loadByTeamId(long teamId, Pageable pageable, DocStatusType status);

	Optional<WorkingDocument> loadById(long workingDocumentId);

	List<User> loadReviewers(WorkingDocument workingDocument);
}
