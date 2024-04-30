package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface GetCpEvaluationsUseCase {
	List<CpEvaluation> getCpEvaluations(User user, WorkingDocument workingDocument);
}
