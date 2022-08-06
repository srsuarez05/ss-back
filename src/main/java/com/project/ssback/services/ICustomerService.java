package com.project.ssback.services;

import com.project.ssback.dto.CustomerRequestDto;
import com.project.ssback.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    public List<Customer> findAll();
    public Page<Customer> findAll(Pageable pageable);
    public Customer findById(Long idCustomer);
    public Customer save(Customer customer);
    public Customer update(Long idCustomer, Customer customer);
    public void delete(Long idCustomer);
}
