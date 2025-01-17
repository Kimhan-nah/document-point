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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cp_payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CpPaymentJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserJpaEntity user;

	@NotNull
	@Column(name = "cp")
	private Integer cp;

	@Builder
	public CpPaymentJpaEntity(Long id, UserJpaEntity user, Integer cp) {
		this.id = id;
		this.user = user;
		this.cp = cp;
	}

	public boolean isUserEmpty() {
		return this.user == null;
	}
}
