package com.unofficialnikke.camerastorejava.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String brand;

    @Column(length = 512)
    private String description;
    private int quantity;

    private double price;

    public ProductEntity(String title, String description, String brand,
                         double price, int quantity, CategoryEntity categoryEntity, SupplierEntity supplierEntity) {
        super();
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.categoryEntity = categoryEntity;
        this.supplierEntity = supplierEntity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", nullable = false)
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier", nullable = false)
    private SupplierEntity supplierEntity;

    public ProductEntity(Long id, String title, String description, String brand, double price, int quantity, CategoryEntity categoryEntity, SupplierEntity supplierEntity) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.categoryEntity = categoryEntity;
        this.supplierEntity = supplierEntity;
    }
}
