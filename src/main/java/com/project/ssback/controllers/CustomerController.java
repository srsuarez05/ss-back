package com.project.ssback.controllers;

import com.project.ssback.dto.CustomerRequestDto;
import com.project.ssback.dto.CustomerResponseDto;
import com.project.ssback.dto.PageDto;
import com.project.ssback.entities.Customer;
import com.project.ssback.services.ICustomerService;
import com.project.ssback.utilities.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.project.ssback.utilities.Constants.*;
import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final ICustomerService customerService;
    private final CustomMapper mapper;

    @GetMapping("/customers-all")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer(){
        List<Customer> customers = customerService.findAll();
        List<CustomerResponseDto> customersDto = customers.stream().map(mapper::toDto).collect(toList());
        return customersDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(customersDto);
    }

    @GetMapping("/customers")
    public ResponseEntity<PageDto<CustomerResponseDto>> getAllCustomerPage(@RequestParam(defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Customer> customers = customerService.findAll(pageable);
        PageDto<CustomerResponseDto> customersPages = mapper.createCustomerPageDto(customers);
        return customersPages.getContent().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(customersPages);
    }

    @GetMapping("/customers/{idCustomer}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable("idCustomer") Long idCustomer){
        Customer customer = customerService.findById(idCustomer);
        return ResponseEntity.ok().body(mapper.toDto(customer));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        Customer customer = customerService.save(mapper.toEntity(customerRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(customer));
    }

    @PutMapping("customers/{idCustomer}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("idCustomer") Long idCustomer,@Valid @RequestBody CustomerRequestDto customerRequestDto){
        Customer customer = customerService.update(idCustomer, mapper.toEntity(customerRequestDto));
        return ResponseEntity.ok().body(mapper.toDto(customer));
    }

    @DeleteMapping("customers/{idCustomer}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("idCustomer") Long idCustomer) {
        customerService.delete(idCustomer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
