package dev.tamal.example.demovalidationspring.mapper;

import dev.tamal.example.demovalidationspring.entity.CustomerEntity;
import dev.tamal.example.demovalidationspring.dto.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    CustomerEntity toEntity(Customer customer);

    Customer toDto(CustomerEntity customerEntity);
    List<Customer> toList(List<CustomerEntity> customerEntities);
}
