package com.docpoint.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cp_evaluation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CpEvaluationJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_reviewer_id")
	private DocumentReviewerJpaEntity documentReviewer;

	@NotNull
	@Column(name = "cp")
	private Integer cp;

	@Size(max = 255)
	@Column(name = "comment")
	private String comment;

	@NotNull
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Builder
	public CpEvaluationJpaEntity(Long id, DocumentReviewerJpaEntity documentReviewer, Integer cp, String comment,
		Boolean isDeleted) {
		this.id = id;
		this.documentReviewer = documentReviewer;
		this.cp = cp;
		this.comment = comment;
		this.isDeleted = isDeleted;
	}

	public boolean isDocumentReviewerEmpty() {
		return this.documentReviewer == null;
	}
}
