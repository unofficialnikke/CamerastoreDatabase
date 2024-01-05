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
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    private Mapper<ProductEntity, ProductDto> productMapper;

    public ProductController(ProductService productService, Mapper<ProductEntity, ProductDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto product) {
        ProductEntity savedProductEntity = productService.save(product);
        ProductDto productDto = productMapper.mapTo(savedProductEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product, @PathVariable("id") Long id) {
        product.setId(id);
        ProductEntity updatedProductEntity = productService.update(product);
        return ResponseEntity.ok(productMapper.mapTo(updatedProductEntity));
    }

    @GetMapping("/products")
    public List<ProductDto> listProducts() {
        List<ProductEntity> products = productService.findAll();
        return products.stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        Optional<ProductEntity> foundProduct = productService.findOne(id);
        return foundProduct.map(productEntity -> {
            ProductDto productDto = productMapper.mapTo(productEntity);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}