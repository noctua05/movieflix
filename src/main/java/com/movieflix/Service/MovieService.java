package com.movieflix.Service;

import com.movieflix.entity.CategoryEntity;
import com.movieflix.entity.MovieEntity;
import com.movieflix.entity.StreamingEntity;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieEntity save(MovieEntity entity){
        entity.setCategory(this.findCategories(entity.getCategory()));
        entity.setStreaming(this.findStreaming(entity.getStreaming()));
        return movieRepository.save(entity);
    }

    public Optional<MovieEntity> findById(Long id){
        return movieRepository.findById(id);
    }

    public Optional<MovieEntity> update(Long id, MovieEntity updateMovie){
        Optional<MovieEntity> optMovie = movieRepository.findById(id);
        if(optMovie.isPresent()){
            MovieEntity movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.setRating(updateMovie.getRating());

            movie.getCategory().clear();
            movie.getCategory().addAll(findCategories(updateMovie.getCategory()));
            movie.getStreaming().clear();
            movie.getStreaming().addAll(findStreaming(updateMovie.getStreaming()));
            return Optional.of(movieRepository.save(movie));
        }
        return Optional.empty();
    }

    public List<MovieEntity> findAll(){
        return movieRepository.findAll();
    }

    public List<MovieEntity> findByCategory(Long categoryID){
        return movieRepository.findMovieByCategory(List.of(CategoryEntity.builder().id(categoryID).build()));
    }

    private List<CategoryEntity> findCategories (List<CategoryEntity> categories){
        List<CategoryEntity> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.findById(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List <StreamingEntity> findStreaming (List<StreamingEntity> streamings){
        List<StreamingEntity> streamingFound = new ArrayList<>();
        streamings.forEach(streaming-> streamingService.findById(streaming.getId()).ifPresent(streamingFound::add));
        return streamingFound;
    }

    public void delete (Long id){
        movieRepository.deleteById(id);
    }

}
