package com.docpoint.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_reviewer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DocumentReviewerJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "working_document_id")
	private WorkingDocumentJpaEntity workingDocument;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewer_id")
	private UserJpaEntity reviewer;

	@Builder
	public DocumentReviewerJpaEntity(WorkingDocumentJpaEntity workingDocument, UserJpaEntity reviewer) {
		this.workingDocument = workingDocument;
		this.reviewer = reviewer;
	}
}
