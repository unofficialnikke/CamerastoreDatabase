package com.unofficialnikke.camerastorejava.repositories;

import com.unofficialnikke.camerastorejava.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
