package com.unofficialnikke.camerastorejava.controllers;

import com.unofficialnikke.camerastorejava.dto.SupplierDto;
import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import com.unofficialnikke.camerastorejava.mappers.Mapper;
import com.unofficialnikke.camerastorejava.services.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SupplierController {

    private SupplierService supplierService;

    private Mapper<SupplierEntity, SupplierDto> supplierMapper;

    public SupplierController(SupplierService supplierService, Mapper<SupplierEntity, SupplierDto> supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @PostMapping("/api/supplier")
    public SupplierDto saveSupplier(@RequestBody SupplierDto supplier) {
        SupplierEntity supplierEntity = supplierMapper.mapFrom(supplier);
        SupplierEntity savedSupplierEntity = supplierService.save(supplierEntity);
        return supplierMapper.mapTo(savedSupplierEntity);
    }

    @GetMapping("/api/suppliers")
    public List<SupplierDto> listSuppliers() {
        List<SupplierEntity> suppliers = supplierService.findAll();
        return suppliers.stream()
                .map(supplierMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/supplier/{id}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable("id") Long id) {
        Optional<SupplierEntity> foundSupplier = supplierService.findOne(id);
        return foundSupplier.map(supplierEntity -> {
            SupplierDto supplierDto = supplierMapper.mapTo(supplierEntity);
            return new ResponseEntity<>(supplierDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
