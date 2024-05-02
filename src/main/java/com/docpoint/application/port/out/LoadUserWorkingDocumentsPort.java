package com.docpoint.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface LoadUserWorkingDocumentsPort {
	Page<WorkingDocument> loadByUser(User user, Pageable pageable);
}
