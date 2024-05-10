package com.docpoint.domain.entity;

import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import lombok.Getter;

@Getter
public class Evaluation {
	private final ReviewQuestionType question;

	private final ReviewAnswerType answer;

	public Evaluation(ReviewQuestionType question, ReviewAnswerType answer) {
		this.question = question;
		this.answer = answer;
	}

	public static Evaluation of(ReviewQuestionType question, ReviewAnswerType answer) {
		return new Evaluation(question, answer);
	}
}
