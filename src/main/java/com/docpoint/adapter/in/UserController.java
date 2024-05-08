package com.docpoint.adapter.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.docpoint.adapter.in.dto.UserResponseDto;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.User;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
public class UserController {
	@GetMapping("/user")
	ResponseEntity<UserResponseDto> getUser(@LoginUser User user) {
		return ResponseEntity.ok(UserResponseDto.from(user));
	}
}
