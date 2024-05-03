package com.docpoint.infrastructure.entity;

import com.docpoint.domain.type.ReviewAnswerType;
import com.docpoint.domain.type.ReviewQuestionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_reviewer_id")
	private DocumentReviewerJpaEntity documentReviewer;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "question")
	private ReviewQuestionType question;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "answer")
	private ReviewAnswerType answer;

	@NotNull
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Builder
	public ReviewJpaEntity(DocumentReviewerJpaEntity documentReviewer, ReviewQuestionType question,
		ReviewAnswerType answer, Boolean isDeleted) {
		this.documentReviewer = documentReviewer;
		this.question = question;
		this.answer = answer;
		this.isDeleted = isDeleted;
	}
}
