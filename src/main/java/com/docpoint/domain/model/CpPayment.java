package com.docpoint.domain.model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class CpPayment {
	private final User user;

	private final int cp;

	public CpPayment(@NonNull User user, int cp) {
		this.user = user;
		this.cp = cp;
	}
}
