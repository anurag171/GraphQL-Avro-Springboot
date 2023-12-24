package com.anurag.spring.mapper;

import com.anurag.spring.dto.CustomerDto;
import com.anurag.spring.entity.Customer;
import com.anurag.spring.service.CustomerService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = CustomerService.class)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);



   // @Mapping(target = "customerName", source = "cucustomerName")
    CustomerDto modelToDto(Customer customer);

    List<CustomerDto> modelsToDtos(List<Customer> customers);

    @InheritInverseConfiguration
    Customer dtoToModel(CustomerDto customerDto);
}
