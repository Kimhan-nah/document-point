package com.docpoint.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class CpPayment {
	private final Long id;
	private final User user;

	private final int cp;

	public CpPayment(User user, int cp) {
		this.id = null;
		this.user = Objects.requireNonNull(user);
		this.cp = cp;
	}
}
