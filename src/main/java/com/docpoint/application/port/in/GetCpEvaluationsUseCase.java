package com.docpoint.application.port.in;

import java.util.Map;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.RoleType;

public interface GetCpEvaluationsUseCase {
	Map<RoleType, CpEvaluation> getCpEvaluations(User user, WorkingDocument workingDocument);
}
