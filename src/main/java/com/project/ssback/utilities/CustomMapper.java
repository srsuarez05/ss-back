package com.project.ssback.utilities;

import com.project.ssback.dto.CustomerRequestDto;
import com.project.ssback.dto.CustomerResponseDto;
import com.project.ssback.dto.PageDto;
import com.project.ssback.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.project.ssback.utilities.Constants.PAGE_CUSTOMERS;
import static java.util.stream.Collectors.toList;

@Component
public class CustomMapper {

    public PageDto<CustomerResponseDto> createCustomerPageDto(Page<Customer> page) {
        PageDto<CustomerResponseDto> pageDto = new PageDto<>();
        pageDto.setContent(page.getContent().stream().map(this::toDto).collect(toList()));
        if (page.hasNext()) {
            pageDto.setNextPage(PAGE_CUSTOMERS + page.nextPageable().getPageNumber());
        }
        if (page.hasPrevious()) {
            pageDto.setPreviousPage(PAGE_CUSTOMERS + page.previousPageable().getPageNumber());
        }
        pageDto.setItemsPerPage(page.getSize());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setTotalPages(page.getTotalPages());
        return pageDto;
    }

    public CustomerResponseDto toDto(Customer customer){
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }

    public Customer toEntity(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .firstname(customerRequestDto.getFirstname())
                .lastname(customerRequestDto.getLastname())
                .email(customerRequestDto.getEmail())
                .dateOfBirth(customerRequestDto.getDateOfBirth())
                .build();
    }

}
