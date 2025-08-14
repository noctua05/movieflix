package com.movieflix.mapper;

import com.movieflix.entity.CategoryEntity;
import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static CategoryEntity toCategoryEntity(CategoryRequest request){
        return CategoryEntity
                .builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse (CategoryEntity entity){
        return CategoryResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
