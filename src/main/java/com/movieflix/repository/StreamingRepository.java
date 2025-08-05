package com.movieflix.repository;

import com.movieflix.entity.StreamingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<StreamingEntity, Long> {
}
