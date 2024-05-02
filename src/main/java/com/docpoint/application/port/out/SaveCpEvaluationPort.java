package com.docpoint.application.port.out;

import com.docpoint.domain.entity.CpEvaluation;

public interface SaveCpEvaluationPort {
	CpEvaluation save(CpEvaluation cpEvaluation);
}
