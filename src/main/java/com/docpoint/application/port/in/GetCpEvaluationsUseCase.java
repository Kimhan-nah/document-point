package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

public interface GetCpEvaluationsUseCase {
	List<CpEvaluation> getCpEvaluations(User user, WorkingDocument workingDocument);
}
