package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.TeamJpaEntity;

public interface TeamRepository extends JpaRepository<TeamJpaEntity, Long> {
}
