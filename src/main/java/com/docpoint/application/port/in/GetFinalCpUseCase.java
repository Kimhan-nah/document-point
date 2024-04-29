package com.docpoint.application.port.in;

import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;

public interface GetFinalCpUseCase {
	CpEvaluation getFinalCp(User user, long workingDocumentId);
}
