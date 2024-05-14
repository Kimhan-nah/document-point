package com.docpoint.infrastructure.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.docpoint.auth.JwtFilter;
import com.docpoint.auth.JwtUtil;
import com.docpoint.auth.LoginFilter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;
	private final JwtUtil jwtUtil;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
			.requestMatchers("/", "/login", "/join").permitAll()
			.requestMatchers("/teams/{team_id}/workingdocs").hasAuthority("PART_LEADER")
			// .requestMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
			.requestMatchers(HttpMethod.POST, "/workingdocs/{workingdoc_id}/cp").hasAuthority("PART_LEADER")
			.requestMatchers(HttpMethod.PUT, "/workingdocs/{workingdoc_id}/cp").hasAuthority("PART_LEADER")
			.requestMatchers(HttpMethod.DELETE, "/workingdocs/{workingdoc_id}/cp").hasAuthority("PART_LEADER")
			.anyRequest().authenticated()
		);

		// disable
		http
			.formLogin(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable);

		// filter
		http
			.cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

					CorsConfiguration configuration = new CorsConfiguration();

					configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
					configuration.setAllowedMethods(Collections.singletonList("*"));
					configuration.setAllowCredentials(true);
					configuration.setAllowedHeaders(Collections.singletonList("*"));
					configuration.setMaxAge(3600L);

					configuration.setExposedHeaders(Collections.singletonList("Authorization"));

					return configuration;
				}
			})));
		http
			.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);

		http
			.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
				UsernamePasswordAuthenticationFilter.class);

		// session management
		http
			.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// spring security
		http
			.exceptionHandling(
				(exceptionHandling) -> exceptionHandling.authenticationEntryPoint(
					new DelegatedAuthenticationEntryPoint()));

		return http.build();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("TEAM_LEADER > PART_LEADER > TEAM_MEMBER");
		return roleHierarchy;
	}
}
