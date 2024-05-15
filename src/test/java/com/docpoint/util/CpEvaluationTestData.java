package com.docpoint.util;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.DocumentReviewer;

public class CpEvaluationTestData {
	private static Long id = 1L;

	public static CpEvaluation createCpEvaluation(DocumentReviewer documentReviewer) {
		return CpEvaluation.builder()
			.id(id++)
			.documentReviewer(documentReviewer)
			.comment("comment")
			.cp(1000)
			.isDeleted(false)
			.build();
	}
}
