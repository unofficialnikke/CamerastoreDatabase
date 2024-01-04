package com.unofficialnikke.camerastorejava.services;

import com.unofficialnikke.camerastorejava.entities.SupplierEntity;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    SupplierEntity save(SupplierEntity supplierEntity);

    List<SupplierEntity> findAll();

    Optional<SupplierEntity> findOne(Long id);
}
