package com.unofficialnikke.camerastorejava.services;

import com.unofficialnikke.camerastorejava.dto.ProductDto;
import com.unofficialnikke.camerastorejava.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductEntity save(ProductDto productDto);

    ProductEntity update(ProductDto productDto);

    List<ProductEntity> findAll();

    Optional<ProductEntity> findOne(Long id);
}
