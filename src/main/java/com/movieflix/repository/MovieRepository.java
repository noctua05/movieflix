package com.movieflix.repository;

import com.movieflix.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
}
