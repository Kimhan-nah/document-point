package com.docpoint.application.port.in;

import com.docpoint.domain.model.CpEvaluation;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

/**
 * 기여도를 입력한다. (cp 평가)
 */
public interface RegisterCpEvaluationUseCase {
	CpEvaluation registerCpEvaluation(CpEvaluation cpEvaluation, WorkingDocument workingDocument, User reviewer);
}
