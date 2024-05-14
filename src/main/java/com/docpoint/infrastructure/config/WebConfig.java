package com.docpoint.infrastructure.config;

import java.util.List;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.docpoint.common.LoginUserArgumentResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer, WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
	private final LoginUserArgumentResolver loginUserArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginUserArgumentResolver);
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p -> {
			p.setOneIndexedParameters(true);    // 1부터 시작
			p.setMaxPageSize(10);                // size=10
		};
	}

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "<>[\\]^`{|}"));
	}
}
