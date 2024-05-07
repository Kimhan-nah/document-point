package com.docpoint.infrastructure.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.docpoint.infrastructure.entity.UserJpaEntity;

public class CustomUserDetails implements UserDetails {
	private final UserJpaEntity userEntity;

	public CustomUserDetails(UserJpaEntity user) {
		this.userEntity = user;
	}

	public UserJpaEntity getUserEntity() {
		return userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();

		collection.add(() -> userEntity.getRole().toString());
		return collection;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getEmployeeNumber();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
