package com.unofficialnikke.camerastorejava.controllers;

import com.unofficialnikke.camerastorejava.entities.Supplier;
import com.unofficialnikke.camerastorejava.repositories.SupplierRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {

    private final SupplierRepository repository;

    public SupplierController(SupplierRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/suppliers")
    public Iterable<Supplier> getSuppliers() {
        return repository.findAll();
    }
}
