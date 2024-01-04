package com.unofficialnikke.camerastorejava.services.impl;

import com.unofficialnikke.camerastorejava.dto.ProductDto;
import com.unofficialnikke.camerastorejava.entities.CategoryEntity;
import com.unofficialnikke.camerastorejava.entities.ProductEntity;
import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import com.unofficialnikke.camerastorejava.repositories.CategoryRepository;
import com.unofficialnikke.camerastorejava.repositories.ProductRepository;
import com.unofficialnikke.camerastorejava.repositories.SupplierRepository;
import com.unofficialnikke.camerastorejava.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductEntity save(ProductDto productDto) {
        SupplierEntity supplierEntity = modelMapper.map(productDto.getSupplier(), SupplierEntity.class);
        CategoryEntity categoryEntity = modelMapper.map(productDto.getCategory(), CategoryEntity.class);
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);

        productEntity.setSupplierEntity(supplierEntity);
        productEntity.setCategoryEntity(categoryEntity);
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductDto productDto) {
        ProductEntity existingProductEntity = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        SupplierEntity supplierEntity = modelMapper.map(productDto.getSupplier(), SupplierEntity.class);
        CategoryEntity categoryEntity = modelMapper.map(productDto.getCategory(), CategoryEntity.class);
        existingProductEntity.setSupplierEntity(supplierEntity);
        existingProductEntity.setCategoryEntity(categoryEntity);
        modelMapper.map(productDto, existingProductEntity);
        return productRepository.save(existingProductEntity);
    }

    @Override
    public List<ProductEntity> findAll() {
        return StreamSupport.stream(productRepository
                        .findAll().spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductEntity> findOne(Long id) {
        return productRepository.findById(id);
    }
}
