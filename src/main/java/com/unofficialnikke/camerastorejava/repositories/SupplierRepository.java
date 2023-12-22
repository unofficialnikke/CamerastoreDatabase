package com.unofficialnikke.camerastorejava.repositories;

import com.unofficialnikke.camerastorejava.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
