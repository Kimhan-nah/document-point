package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;

public interface RegisterWorkingDocumentUseCase {
	void registerWorkingDocument(WorkingDocument workingDocument, Working working, User user, List<User> reviewers);

	void updateWorkingDocument(WorkingDocument from, WorkingDocument to, User user, List<User> reviewers);
}
