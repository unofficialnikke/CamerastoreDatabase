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
@RequestMapping("/api")
public class SupplierController {

    private SupplierService supplierService;

    private Mapper<SupplierEntity, SupplierDto> supplierMapper;

    public SupplierController(SupplierService supplierService, Mapper<SupplierEntity, SupplierDto> supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDto> saveSupplier(@RequestBody SupplierDto supplier) {
        SupplierEntity supplierEntity = supplierMapper.mapFrom(supplier);
        SupplierEntity savedSupplierEntity = supplierService.save(supplierEntity);
        SupplierDto supplierDto = supplierMapper.mapTo(savedSupplierEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierDto);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@RequestBody SupplierDto supplier, @PathVariable("id") Long id) {
        SupplierEntity supplierEntity = supplierMapper.mapFrom(supplier);
        supplierEntity.setId(id);
        SupplierEntity updatedSupplierEntity = supplierService.update(supplierEntity);
        SupplierDto updatedSupplierDto = supplierMapper.mapTo(updatedSupplierEntity);
        return ResponseEntity.ok(updatedSupplierDto);
    }

    @GetMapping("/suppliers")
    public List<SupplierDto> listSuppliers() {
        List<SupplierEntity> suppliers = supplierService.findAll();
        return suppliers.stream()
                .map(supplierMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable("id") Long id) {
        Optional<SupplierEntity> foundSupplier = supplierService.findOne(id);
        return foundSupplier.map(supplierEntity -> {
            SupplierDto supplierDto = supplierMapper.mapTo(supplierEntity);
            return new ResponseEntity<>(supplierDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
