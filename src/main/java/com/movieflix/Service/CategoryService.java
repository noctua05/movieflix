package com.movieflix.Service;

import com.movieflix.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movieflix.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll(){
     return categoryRepository.findAll();
    }

    public CategoryEntity saveCategory(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }

    public Optional<CategoryEntity> findById(Long id){
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
