package com.movieflix.repository;

import com.movieflix.entity.StreamingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository extends JpaRepository<StreamingEntity, Long> {
}
