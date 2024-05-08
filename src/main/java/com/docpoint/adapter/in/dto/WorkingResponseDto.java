package com.docpoint.adapter.in.dto;

import java.util.ArrayList;
import java.util.List;

import com.docpoint.domain.entity.Working;

import lombok.Getter;

@Getter
public class WorkingResponseDto {
	private final List<WorkingDto> workings = new ArrayList<>();

	public WorkingResponseDto(List<WorkingDto> workings) {
		this.workings.addAll(workings);
	}

	public static WorkingResponseDto from(List<Working> workingList) {
		List<WorkingDto> workings = new ArrayList<>();
		for (Working working : workingList) {
			WorkingDto workingDto = WorkingDto.from(working);
			workings.add(workingDto);
		}
		return new WorkingResponseDto(workings);
	}
}
