package com.unofficialnikke.camerastorejava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, email, phone;

    public SupplierEntity(String name, String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "supplierEntity")
    private List<ProductEntity> productEntities;

}
