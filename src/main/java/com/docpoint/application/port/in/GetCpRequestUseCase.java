package com.docpoint.application.port.in;

import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface GetCpRequestUseCase {
	CpEvaluation getCpRequest(User user, WorkingDocument workingDocument);
}
