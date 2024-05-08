package com.docpoint.adapter.in.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class WorkingResponseDto {
	private final List<WorkingDto> workings = new ArrayList<>();

	public WorkingResponseDto(List<WorkingDto> workings) {
		this.workings.addAll(workings);
	}

	public static WorkingResponseDto from(List<WorkingDto> workings) {
		return new WorkingResponseDto(workings);
	}
}
