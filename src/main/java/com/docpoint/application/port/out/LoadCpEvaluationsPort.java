package com.docpoint.application.port.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.domain.model.CpEvaluation;

public interface LoadCpEvaluationsPort {
	List<CpEvaluation> loadByWorkingDocumentId(long workingDocumentId);

	Optional<CpEvaluation> loadFinalCpByWorkingDocumentId(long workingDocumentId);
}
