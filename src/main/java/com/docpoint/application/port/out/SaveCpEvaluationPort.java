package com.docpoint.application.port.out;

import com.docpoint.domain.model.CpEvaluation;

public interface SaveCpEvaluationPort {
	CpEvaluation save(CpEvaluation cpEvaluation);
}
