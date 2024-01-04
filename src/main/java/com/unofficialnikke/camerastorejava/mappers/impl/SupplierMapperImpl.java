package com.unofficialnikke.camerastorejava.mappers.impl;

import com.unofficialnikke.camerastorejava.dto.SupplierDto;
import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import com.unofficialnikke.camerastorejava.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapperImpl implements Mapper<SupplierEntity, SupplierDto> {

    private ModelMapper modelMapper;

    public SupplierMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierDto mapTo(SupplierEntity supplierEntity) {
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }

    @Override
    public SupplierEntity mapFrom(SupplierDto supplierDto) {
        return modelMapper.map(supplierDto, SupplierEntity.class);
    }
}
