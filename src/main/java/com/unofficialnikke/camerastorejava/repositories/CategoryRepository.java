package com.unofficialnikke.camerastorejava.repositories;

import com.unofficialnikke.camerastorejava.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
