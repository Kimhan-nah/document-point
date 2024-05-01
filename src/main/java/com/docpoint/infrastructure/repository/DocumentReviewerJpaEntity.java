package com.docpoint.infrastructure.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_reviewer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DocumentReviewerJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "working_document_id")
	private WorkingDocumentJpaEntity workingDocument;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserJpaEntity user;

	public DocumentReviewerJpaEntity(WorkingDocumentJpaEntity workingDocument, UserJpaEntity user) {
		this.workingDocument = workingDocument;
		this.user = user;
	}
}
