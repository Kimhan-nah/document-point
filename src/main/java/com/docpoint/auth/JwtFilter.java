package com.docpoint.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.docpoint.domain.type.RoleType;
import com.docpoint.infrastructure.entity.UserJpaEntity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		String token = jwtUtil.getToken(request);
		if (token == null) {
			System.out.println("token null");
			filterChain.doFilter(request, response);
			return;
		}

		// 토큰 시간 검증
		if (jwtUtil.isExpired(token)) {
			System.out.println("token expired");
			filterChain.doFilter(request, response);
			return;
		}

		String employeeId = jwtUtil.getEmployeeId(token);
		String role = jwtUtil.getRole(token);
		UserJpaEntity userEntity = UserJpaEntity.builder()
			.employeeId(employeeId)
			.password("tmp-password")
			.role(RoleType.valueOf(role))
			.name("tmp-name")
			.build();

		//UserDetails에 회원 정보 객체 담기
		CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

		//스프링 시큐리티 인증 토큰 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
			customUserDetails.getAuthorities());

		//세션에 사용자 등록
		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}
}
