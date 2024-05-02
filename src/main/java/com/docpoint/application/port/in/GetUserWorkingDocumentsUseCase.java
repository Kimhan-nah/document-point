package com.docpoint.application.port.in;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface GetUserWorkingDocumentsUseCase {
	List<WorkingDocument> getUserWorkingDocuments(User user, Pageable pageable);
}
