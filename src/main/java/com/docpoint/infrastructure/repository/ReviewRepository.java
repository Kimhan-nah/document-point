package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.ReviewJpaEntity;

public interface ReviewRepository extends JpaRepository<ReviewJpaEntity, Long> {
}
