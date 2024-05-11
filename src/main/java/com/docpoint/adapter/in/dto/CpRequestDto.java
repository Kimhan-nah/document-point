package com.docpoint.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CpRequestDto {
	@NotNull(message = "cp가 비어있습니다.")
	@Size(min = 0, max = 2147483647, message = "cp는 0~2147483647 사이의 값으로 입력해주세요.")
	private Integer cp;

	@NotBlank(message = "comment가 비어있습니다.")
	@Size(max = 255, message = "comment는 255자 이하로 입력해주세요.")
	private String comment;
}
