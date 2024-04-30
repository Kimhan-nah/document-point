package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface LoadCpEvaluationsPort {
	List<CpEvaluation> loadByWorkingDocument(WorkingDocument workingDocument);

	Optional<CpEvaluation> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user);
}
