package com.docpoint.infrastructure.entity;

import com.docpoint.domain.type.RoleType;

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
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private TeamJpaEntity team;

	@NotNull
	@Column(name = "employee_number")
	private Integer employeeNumber;

	@NotNull
	@Size(max = 255)
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(max = 255)
	@Column(name = "email")
	private String email;

	@NotNull
	@Size(max = 255)
	@Column(name = "password")
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleType role;

	@NotNull
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Builder
	public UserJpaEntity(TeamJpaEntity team, Integer employeeNumber, String name, String email, String password,
		RoleType role, Boolean isDeleted) {
		this.team = team;
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.isDeleted = isDeleted;
	}
}
