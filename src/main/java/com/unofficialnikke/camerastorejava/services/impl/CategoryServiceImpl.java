package com.unofficialnikke.camerastorejava.services.impl;

import com.unofficialnikke.camerastorejava.entities.CategoryEntity;
import com.unofficialnikke.camerastorejava.repositories.CategoryRepository;
import com.unofficialnikke.camerastorejava.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        if (categoryEntity.getId() == null || categoryEntity.getId() == 0) {
            throw new IllegalArgumentException("Category ID cannot be null or 0");
        }
        CategoryEntity existingCategoryEntity = categoryRepository.findById(categoryEntity.getId())
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
        existingCategoryEntity.setName(categoryEntity.getName());
        return categoryRepository.save(existingCategoryEntity);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return StreamSupport.stream(categoryRepository
                        .findAll().spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryEntity> findOne(Long id) {
        return categoryRepository.findById(id);
    }
}
