package com.unofficialnikke.camerastorejava.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title, brand, description;
    private int quantity;
    private double price;

    private CategoryDto category;
    private SupplierDto supplier;

}
