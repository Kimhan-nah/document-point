package com.docpoint.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

public interface LoadUserWorkingDocumentPort {
	Page<WorkingDocument> loadByUser(User user, Pageable pageable, DocStatusType status);
}
