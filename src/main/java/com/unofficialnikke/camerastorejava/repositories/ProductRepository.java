package com.unofficialnikke.camerastorejava.repositories;

import com.unofficialnikke.camerastorejava.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
List<Product> findByBrand(String brand);

List<Product> findByBrandOrTitle(String brand, String title);
}
