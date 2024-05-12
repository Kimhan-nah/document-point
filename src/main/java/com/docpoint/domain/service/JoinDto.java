package com.docpoint.domain.service;

import com.docpoint.domain.type.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class JoinDto {

	@NotBlank
	private String name;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotBlank
	private String employeeId;

	@NotNull
	private RoleType role;

	@NotNull
	@Positive
	private Long teamId;

}
