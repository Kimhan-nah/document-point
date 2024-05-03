package com.docpoint.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "working_assignee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkingAssigneeJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "working_id")
	private WorkingJpaEntity working;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_id")
	private UserJpaEntity assignee;

	@Builder
	public WorkingAssigneeJpaEntity(WorkingJpaEntity working, UserJpaEntity assignee) {
		this.working = working;
		this.assignee = assignee;
	}
}
