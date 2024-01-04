package com.unofficialnikke.camerastorejava.services;

import com.unofficialnikke.camerastorejava.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity update(CategoryEntity categoryEntity);

    List<CategoryEntity> findAll();

    Optional<CategoryEntity> findOne(Long id);
}
