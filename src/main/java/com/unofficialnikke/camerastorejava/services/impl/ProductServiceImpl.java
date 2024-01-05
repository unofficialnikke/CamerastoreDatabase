package com.unofficialnikke.camerastorejava.services.impl;

import com.unofficialnikke.camerastorejava.dto.CategoryDto;
import com.unofficialnikke.camerastorejava.dto.ProductDto;
import com.unofficialnikke.camerastorejava.dto.SupplierDto;
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
import java.util.function.Supplier;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    private ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    private SupplierEntity mapToSupplierEntity(SupplierDto supplierDto) {
        return modelMapper.map(supplierDto, SupplierEntity.class);
    }

    private CategoryEntity mapToCategoryEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, CategoryEntity.class);
    }

    @Override
    public ProductEntity save(ProductDto productDto) {
        SupplierEntity supplierEntity = mapToSupplierEntity(productDto.getSupplier());
        CategoryEntity categoryEntity = mapToCategoryEntity(productDto.getCategory());
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        productEntity.setSupplierEntity(supplierEntity);
        productEntity.setCategoryEntity(categoryEntity);
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductDto productDto) {
        ProductEntity existingProductEntity = getProductById(productDto.getId());
        SupplierEntity supplierEntity = mapToSupplierEntity(productDto.getSupplier());
        CategoryEntity categoryEntity = mapToCategoryEntity(productDto.getCategory());
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
