package com.docpoint.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CpPayment {
	private final Long id;
	private final User user;
	private final int cp;

	@Builder
	public CpPayment(Long id, User user, int cp) {
		this.id = id;
		this.user = user;
		this.cp = cp;
	}
}
