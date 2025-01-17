package com.docpoint.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;

import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "working_document")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkingDocumentJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "working_id")
	private WorkingJpaEntity working;

	@Column(name = "cp")
	private Integer cp; // nullable

	@NotNull
	@Size(max = 255)
	@Column(name = "title")
	private String title;

	@NotNull
	@Size(max = 1000)
	@Column(name = "content")
	private String content;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private DocStatusType status;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private DocType type;

	@NotNull
	@Size(max = 255)
	@Column(name = "link")
	private String link;

	@NotNull
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@OneToMany(mappedBy = "workingDocument", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<DocumentReviewerJpaEntity> reviewers = new ArrayList<>();

	@Builder
	public WorkingDocumentJpaEntity(Long id, WorkingJpaEntity working, Integer cp, String title, String content,
		DocStatusType status, DocType type, String link, Boolean isDeleted) {
		this.id = id;
		this.working = working;
		this.cp = cp;
		this.title = title;
		this.content = content;
		this.status = status;
		this.type = type;
		this.link = link;
		this.isDeleted = isDeleted;
	}

	public boolean isWorkingEmpty() {
		return this.working == null;
	}

	/**
	 * DocumentReviewer에서 호출하는 연관관계 설정 메서드
	 * 기타 호출 금지
	 */
	protected void addDocumentReviewer(DocumentReviewerJpaEntity documentReviewer) {
		this.reviewers.add(documentReviewer);
	}

	public void updateStatus(DocStatusType status) {
		this.status = status;
	}
}
