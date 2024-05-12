package com.docpoint.adapter.in.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CpRequestDto {
	@NotNull(message = "cp가 비어있습니다.")
	@Min(value = 0, message = "cp는 0 이상의 값으로 입력해주세요.")
	@Max(value = 2147483647, message = "cp는 2147483647 이하의 값으로 입력해주세요.")
	private Integer cp;

	@Size(max = 255, message = "comment는 255자 이하로 입력해주세요.")
	private String comment;
}
