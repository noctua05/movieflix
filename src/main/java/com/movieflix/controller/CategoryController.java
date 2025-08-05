package com.movieflix.controller;

import com.movieflix.Service.CategoryService;
import com.movieflix.entity.CategoryEntity;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.request.CategoryRequest;
import com.movieflix.response.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request){
        CategoryEntity entity = CategoryMapper.toCategoryEntity(request);
        CategoryEntity savedEntity = categoryService.saveCategory(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedEntity));
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findCategory(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(entity -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(entity)))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
