package com.unofficialnikke.camerastorejava.controllers;

import com.unofficialnikke.camerastorejava.entities.Product;
import com.unofficialnikke.camerastorejava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {
<<<<<<< HEAD
    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
=======
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello guys";
>>>>>>> cb3d97ea23cfe5e430b9e8aff323ab5b2b094c1c
    }

    @GetMapping("/api/products")
    public Iterable<Product> getProducts() {
        return repository.findAll();
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}