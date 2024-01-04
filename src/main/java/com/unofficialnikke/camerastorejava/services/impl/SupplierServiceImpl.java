package com.unofficialnikke.camerastorejava.services.impl;

import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import com.unofficialnikke.camerastorejava.repositories.SupplierRepository;
import com.unofficialnikke.camerastorejava.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierEntity save(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
    }

    @Override
    public SupplierEntity update(SupplierEntity supplierEntity) {
        if (supplierEntity.getId() == null || supplierEntity.getId() == 0) {
            throw new IllegalArgumentException("Supplier ID cannot be null or 0");
        }
        SupplierEntity existingSupplierEntity = supplierRepository.findById(supplierEntity.getId())
                .orElseThrow(() -> new NoSuchElementException("Supplier not found"));
        modelMapper.map(supplierEntity, existingSupplierEntity);
        return supplierRepository.save(existingSupplierEntity);
    }

    @Override
    public List<SupplierEntity> findAll() {
        return StreamSupport.stream(supplierRepository.
                        findAll().spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierEntity> findOne(Long id) {
        return supplierRepository.findById(id);
    }
}
