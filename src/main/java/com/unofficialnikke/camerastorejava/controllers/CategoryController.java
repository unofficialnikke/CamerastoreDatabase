package com.unofficialnikke.camerastorejava.controllers;

import com.unofficialnikke.camerastorejava.dto.CategoryDto;
import com.unofficialnikke.camerastorejava.dto.SupplierDto;
import com.unofficialnikke.camerastorejava.entities.CategoryEntity;
import com.unofficialnikke.camerastorejava.mappers.Mapper;
import com.unofficialnikke.camerastorejava.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    private Mapper<CategoryEntity, CategoryDto> categoryMapper;

    public CategoryController(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/api/category")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto category) {
        CategoryEntity categoryEntity = categoryMapper.mapFrom(category);
        CategoryEntity savedCategoryEntity = categoryService.save(categoryEntity);
        CategoryDto categoryDto = categoryMapper.mapTo(savedCategoryEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @PatchMapping("/api/category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto category, @PathVariable("id") Long id) {
        CategoryEntity categoryEntity = categoryMapper.mapFrom(category);
        categoryEntity.setId(id);
        CategoryEntity updatedCategoryEntity = categoryService.update(categoryEntity);
        CategoryDto updatedCategoryDto = categoryMapper.mapTo(updatedCategoryEntity);
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @GetMapping("/api/categories")
    public List<CategoryDto> listCategories() {
        List<CategoryEntity> categories = categoryService.findAll();
        return categories.stream()
                .map(categoryMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
        Optional<CategoryEntity> foundCategory = categoryService.findOne(id);
        return foundCategory.map(categoryEntity -> {
            CategoryDto categoryDto = categoryMapper.mapTo(categoryEntity);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
