package com.unofficialnikke.camerastorejava.controllers;

import com.unofficialnikke.camerastorejava.entities.Category;
import com.unofficialnikke.camerastorejava.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/categories")
    public Iterable<Category> getCategories() {
        return repository.findAll();
    }
}
