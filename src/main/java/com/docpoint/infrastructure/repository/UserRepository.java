package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.UserJpaEntity;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
}
