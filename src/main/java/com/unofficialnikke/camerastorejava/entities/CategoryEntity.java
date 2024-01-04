package com.unofficialnikke.camerastorejava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CategoryEntity(String name) {
        super();
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities;

}
