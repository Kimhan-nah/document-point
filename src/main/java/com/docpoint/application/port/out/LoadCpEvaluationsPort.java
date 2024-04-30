package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface LoadCpEvaluationsPort {
	List<CpEvaluation> loadByWorkingDocumentId(long workingDocumentId);

	Optional<CpEvaluation> loadFinalCpByWorkingDocumentId(long workingDocumentId);

	Optional<CpEvaluation> loadCpRequestByWorkingDocumentId(long workingDocumentId);

	Optional<CpEvaluation> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user);
}
