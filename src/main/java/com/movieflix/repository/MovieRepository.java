package com.movieflix.repository;

import com.movieflix.entity.CategoryEntity;
import com.movieflix.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<MovieEntity,Long> {

    List<MovieEntity> findMovieByCategory (List<CategoryEntity> categories);
}
