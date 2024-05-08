package com.docpoint.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

public interface GetUserWorkingDocumentsUseCase {
	Page<WorkingDocument> getUserWorkingDocuments(User user, Pageable pageable, DocStatusType status);
}
