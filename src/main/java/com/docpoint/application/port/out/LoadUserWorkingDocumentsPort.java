package com.docpoint.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.WorkingDocument;

public interface LoadUserWorkingDocumentsPort {
	Page<WorkingDocument> loadByUserId(long userId, Pageable pageable);
}