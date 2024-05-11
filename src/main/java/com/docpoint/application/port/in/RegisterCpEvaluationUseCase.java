package com.docpoint.application.port.in;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

/**
 * 기여도를 입력한다. (cp 평가)
 */
public interface RegisterCpEvaluationUseCase {
	void registerCpEvaluation(CpEvaluation cpEvaluation, WorkingDocument workingDocument, User reviewer);
}
