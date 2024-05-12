package com.docpoint.adapter.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.docpoint.adapter.in.dto.UserResponseDto;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.service.JoinDto;
import com.docpoint.domain.service.JoinService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
public class UserController {
	private final JoinService joinService;

	@GetMapping("/user")
	ResponseEntity<UserResponseDto> getUser(@LoginUser User user) {
		return ResponseEntity.ok(UserResponseDto.from(user));
	}

	@PostMapping("/join")
	ResponseEntity<Void> join(@RequestBody @Valid JoinDto joinDto) {
		joinService.joinProcess(joinDto);
		return ResponseEntity.ok().build();
	}
}
