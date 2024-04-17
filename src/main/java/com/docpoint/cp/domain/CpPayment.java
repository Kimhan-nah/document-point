package com.docpoint.cp.domain;

import com.docpoint.user.domain.User;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class CpPayment {
	@NonNull
	private final User user;

	private final int cp;

	public CpPayment(@NonNull User user, int cp) {
		this.user = user;
		this.cp = cp;
	}
}
