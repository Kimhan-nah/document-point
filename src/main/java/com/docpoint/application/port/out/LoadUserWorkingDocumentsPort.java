package com.docpoint.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.WorkingDocument;

public interface LoadUserWorkingDocumentsPort {
	List<WorkingDocument> loadByUserId(Long userId, Pageable pageable);
}
