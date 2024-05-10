package com.docpoint.infrastructure.entity;

import java.time.LocalDateTime;

import com.docpoint.domain.type.WorkingCategoryType;
import com.docpoint.domain.type.WorkingStatusType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "working")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkingJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer_id")
	private UserJpaEntity writer;

	@NotNull
	@Column(name = "cp")
	private Integer cp;

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
	private WorkingStatusType status;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private WorkingCategoryType category;

	@NotNull
	@Column(name = "due_date")
	private LocalDateTime dueDate;

	@NotNull
	@Column(name = "recruit_date")
	private LocalDateTime recruitDate;

	@NotNull
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@OneToOne(mappedBy = "working", fetch = FetchType.LAZY)
	private WorkingAssigneeJpaEntity assignee;

	@Builder
	public WorkingJpaEntity(Long id, UserJpaEntity writer, Integer cp, String title, String content,
		WorkingStatusType status,
		WorkingCategoryType category, LocalDateTime dueDate, LocalDateTime recruitDate, Boolean isDeleted) {
		this.id = id;
		this.writer = writer;
		this.cp = cp;
		this.title = title;
		this.content = content;
		this.status = status;
		this.category = category;
		this.dueDate = dueDate;
		this.recruitDate = recruitDate;
		this.isDeleted = isDeleted;
	}

	public boolean isUserEmpty() {
		return this.writer == null;
	}
}
