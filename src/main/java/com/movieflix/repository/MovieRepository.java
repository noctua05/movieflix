package com.movieflix.repository;

import com.movieflix.entity.CategoryEntity;
import com.movieflix.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {

    List<MovieEntity> findMovieByCategory (List<CategoryEntity> categories);
}
