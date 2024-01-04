package com.unofficialnikke.camerastorejava.repositories;

import com.unofficialnikke.camerastorejava.entities.CategoryEntity;
import com.unofficialnikke.camerastorejava.entities.ProductEntity;
import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductEntityRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;
    private ProductEntity savedProductEntity;
    private CategoryEntity savedCategoryEntity;
    private SupplierEntity savedSupplier;

    @BeforeEach
    public void setUp() {
        CategoryEntity categoryEntity = new CategoryEntity();
        savedCategoryEntity = categoryRepository.save(categoryEntity);

        SupplierEntity supplierEntity = new SupplierEntity();
        savedSupplier = supplierRepository.save(supplierEntity);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle("Nikon Z6");
        productEntity.setPrice(1200);
        productEntity.setDescription("Nice camera");
        productEntity.setBrand("Nikon");
        productEntity.setCategoryEntity(savedCategoryEntity);
        productEntity.setSupplierEntity(savedSupplier);
        savedProductEntity = productRepository.save(productEntity);
    }

    @Test
    public void testSaveProduct() {
        assertNotNull(savedProductEntity.getId());
        assertEquals("Nikon Z6", savedProductEntity.getTitle());
        assertEquals("Nice camera", savedProductEntity.getDescription());
        assertEquals(1200, savedProductEntity.getPrice());
    }

    @Test
    public void testFindByName() {
        Optional<ProductEntity> foundProduct = productRepository.findByTitle("Nikon Z6");
        assertTrue(foundProduct.isPresent());
        assertEquals("Nikon Z6", foundProduct.get().getTitle());
    }

    @Test
    public void testFindAllProducts() {
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepository.findAll();
        assertNotNull(productEntities);
        assertFalse(productEntities.isEmpty());
        assertTrue(productEntities.contains(savedProductEntity));
    }
}
