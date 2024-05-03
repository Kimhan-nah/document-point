package com.docpoint.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.domain.type.RoleType;
import com.docpoint.infrastructure.entity.UserJpaEntity;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
	List<UserJpaEntity> findByTeam_Id(Long teamId);

	List<UserJpaEntity> findByTeam_IdAndRole(Long teamId, RoleType role);
}
