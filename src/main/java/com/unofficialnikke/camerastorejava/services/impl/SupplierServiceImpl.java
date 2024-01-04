package com.unofficialnikke.camerastorejava.services.impl;

import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import com.unofficialnikke.camerastorejava.repositories.SupplierRepository;
import com.unofficialnikke.camerastorejava.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierEntity save(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
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
