package com.example.mapper;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import io.micronaut.context.annotation.Mapper;

@Mapper
public interface CustomerMapper {
    Customer toEntity(CustomerDTO dto);
}
