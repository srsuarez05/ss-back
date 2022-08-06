package com.project.ssback.services;

import com.project.ssback.dto.CustomerRequestDto;
import com.project.ssback.entities.Customer;
import com.project.ssback.exceptions.CustomerNotFoundException;
import com.project.ssback.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(
                () -> new CustomerNotFoundException(idCustomer));
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer update(Long idCustomer, Customer customer) {
        Customer currentCustomer = customerRepository.findById(idCustomer).orElseThrow(
                () -> new CustomerNotFoundException(idCustomer));
        currentCustomer.setFirstname(customer.getFirstname());
        currentCustomer.setLastname(customer.getLastname());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setDateOfBirth(customer.getDateOfBirth());
        return currentCustomer;
    }

    @Override
    @Transactional
    public void delete(Long idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(
                () -> new CustomerNotFoundException(idCustomer));
        customerRepository.delete(customer);
    }
}
