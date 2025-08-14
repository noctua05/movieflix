package com.movieflix.mapper;

import com.movieflix.entity.CategoryEntity;
import com.movieflix.entity.MovieEntity;
import com.movieflix.entity.StreamingEntity;
import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.controller.response.StreamingResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static MovieEntity toMovieEntity(MovieRequest request){

        List<CategoryEntity> categories = request.categories().stream()
                .map(categoryID -> CategoryEntity.builder().id(categoryID).build())
                .toList();

        List<StreamingEntity> streamings = request.streamings().stream()
                .map(streamingID -> StreamingEntity.builder().id(streamingID).build())
                .toList();

        return MovieEntity.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .category(categories)
                .streaming(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse (MovieEntity entity){

        List<CategoryResponse> categories= entity.getCategory().stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = entity.getStreaming()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming)).toList();

        return MovieResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .releaseDate(entity.getReleaseDate())
                .rating(entity.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
