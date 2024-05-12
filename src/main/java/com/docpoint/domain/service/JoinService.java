package com.docpoint.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.repository.TeamRepository;
import com.docpoint.infrastructure.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final TeamRepository teamRepository;

	@Transactional
	public void joinProcess(JoinDto joinDto) {

		userRepository.findByEmployeeId(joinDto.getEmployeeId())
			.ifPresent(user -> {
				throw new IllegalArgumentException("이미 가입된 사용자입니다.");
			});

		TeamJpaEntity teamJpaEntity = teamRepository.getReferenceById(joinDto.getTeamId());

		UserJpaEntity userJpaEntity = UserJpaEntity.builder()
			.name(joinDto.getName())
			.email(joinDto.getEmail())
			.password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
			.employeeId(joinDto.getEmployeeId())
			.role(joinDto.getRole())
			.team(teamJpaEntity)
			.isDeleted(false)
			.build();

		userRepository.save(userJpaEntity);
	}
}
