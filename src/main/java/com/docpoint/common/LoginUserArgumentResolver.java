package com.docpoint.common;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.docpoint.application.port.out.LoadUserPort;
import com.docpoint.auth.JwtUtil;
import com.docpoint.domain.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
	private final LoadUserPort loadUserPort;
	private final JwtUtil jwtUtil;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(
			com.docpoint.common.annotation.LoginUser.class);
		boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());
		return hasLoginUserAnnotation && isUserType;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String token = jwtUtil.getToken((HttpServletRequest)webRequest.getNativeRequest());
		String employeeNumber = jwtUtil.getEmployeeId(token);
		return loadUserPort.loadByEmployeeId(employeeNumber).orElseThrow();
	}
}
