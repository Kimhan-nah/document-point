package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.type.RoleType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CpDto {
	private final UserDto user;
	private final Integer cp;
	private final String comment;

	@Builder
	public CpDto(UserDto user, Integer cp, String comment) {
		this.user = user;
		this.cp = cp;
		this.comment = comment;
	}

	public static CpDto toDto(RoleType role, CpEvaluation cpEvaluation) {
		if (cpEvaluation == null) {
			return null;
		}
		return CpDto.builder()
			.user(UserDto.toDto(cpEvaluation.getDocumentReviewer().getReviewer()))
			.cp(cpEvaluation.getCp())
			.comment(role == RoleType.TEAM_MEMBER ? null : cpEvaluation.getComment())
			.build();
	}
}
