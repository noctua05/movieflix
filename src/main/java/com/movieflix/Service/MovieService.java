package com.movieflix.Service;

import com.movieflix.entity.CategoryEntity;
import com.movieflix.entity.MovieEntity;
import com.movieflix.entity.StreamingEntity;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<MovieEntity> findAll(){
        return movieRepository.findAll();
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

}
