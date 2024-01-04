package com.unofficialnikke.camerastorejava.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDto {

    private Long id;

    private String name, email, phone;
}
