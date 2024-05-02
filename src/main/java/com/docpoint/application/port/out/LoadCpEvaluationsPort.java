package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface LoadCpEvaluationsPort {
	List<CpEvaluation> loadByWorkingDocument(WorkingDocument workingDocument);

	Optional<CpEvaluation> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user);
}
