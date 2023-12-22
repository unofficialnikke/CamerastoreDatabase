package com.unofficialnikke.camerastorejava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        super();
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products;


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCategoryId(Long categoryId) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
