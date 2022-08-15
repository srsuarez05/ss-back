package com.project.ssback.utilities;

import com.project.ssback.dto.*;
import com.project.ssback.entities.Customer;
import com.project.ssback.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.project.ssback.utilities.Constants.PAGE_CUSTOMERS;
import static java.util.stream.Collectors.toList;

@Component
public class CustomMapper {

    public PageDto<CustomerResponseDto> createCustomerPageDto(Page<Customer> page) {
        PageDto<CustomerResponseDto> pageDto = new PageDto<>();
        pageDto.setContent(page.getContent().stream().map(this::toCustomerDto).collect(toList()));
        if (page.hasNext()) {
            pageDto.setNextPage(page.nextPageable().getPageNumber());
        }
        if (page.hasPrevious()) {
            pageDto.setPreviousPage(page.previousPageable().getPageNumber());
        }
        pageDto.setItemsPerPage(page.getSize());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setTotalPages(page.getTotalPages());
        return pageDto;
    }

    public CustomerResponseDto toCustomerDto(Customer customer){
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .dateOfBirth(customer.getDateOfBirth())
                .photo(customer.getPhoto())
                .region(customer.getRegion())
                .build();
    }

    public Customer toCustomerEntity(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .firstname(customerRequestDto.getFirstname())
                .lastname(customerRequestDto.getLastname())
                .email(customerRequestDto.getEmail())
                .dateOfBirth(customerRequestDto.getDateOfBirth())
                .photo(customerRequestDto.getPhoto())
                .region(customerRequestDto.getRegion())
                .build();
    }

    public RegionResponseDto toRegionDto(Region region){
        return RegionResponseDto.builder()
                .id(region.getId())
                .name(region.getName())
                .build();
    }

    public Region toRegionEntity(RegionRequestDto regionRequestDto){
        return Region.builder()
                .name(regionRequestDto.getName())
                .build();
    }

}
