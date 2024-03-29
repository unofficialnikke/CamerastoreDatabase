package com.unofficialnikke.camerastorejava.controllers;

import com.unofficialnikke.camerastorejava.dto.ProductDto;
import com.unofficialnikke.camerastorejava.entities.ProductEntity;
import com.unofficialnikke.camerastorejava.mappers.Mapper;
import com.unofficialnikke.camerastorejava.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private ProductService productService;

    private Mapper<ProductEntity, ProductDto> productMapper;

    public ProductController(ProductService productService, Mapper<ProductEntity, ProductDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/api/product")
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        ProductEntity productEntity = productMapper.mapFrom(product);
        ProductEntity savedProductEntity = productService.save(product);
        return productMapper.mapTo(savedProductEntity);
    }

    @GetMapping("/api/products")
    public List<ProductDto> listProducts() {
        List<ProductEntity> products = productService.findAll();
        return products.stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        Optional<ProductEntity> foundProduct = productService.findOne(id);
        return foundProduct.map(productEntity -> {
            ProductDto productDto = productMapper.mapTo(productEntity);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}