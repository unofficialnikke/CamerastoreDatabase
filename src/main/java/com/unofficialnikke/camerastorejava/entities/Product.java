package com.unofficialnikke.camerastorejava.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String brand;

    @Column(nullable = false, length = 512)
    private String description;
    private int quantity;

    private double price;

    public Product() {
    }

    public Product(String title, String description, String brand,
                   int price, int quantity, Category category, Supplier supplier) {
        super();
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplier = supplier;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier")
    private Supplier supplier;

}
