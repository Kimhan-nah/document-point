package com.docpoint.adapter.in.dto;

import lombok.Getter;

@Getter
public class CpResponseDto {
	private final CpDto request;
	private final CpDto approval;

	public CpResponseDto(CpDto request, CpDto approval) {
		this.request = request;
		this.approval = approval;
	}
}
