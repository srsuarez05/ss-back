package com.project.ssback.services.impl;

import com.project.ssback.entities.Customer;
import com.project.ssback.exceptions.ExistsEmailException;
import com.project.ssback.exceptions.ResourceNotFoundException;
import com.project.ssback.repositories.CustomerRepository;
import com.project.ssback.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.project.ssback.utilities.Constants.CUSTOMER;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

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
                () -> new ResourceNotFoundException(CUSTOMER, idCustomer));
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ExistsEmailException();
        }
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer update(Long idCustomer, Customer currentCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(
                () -> new ResourceNotFoundException(CUSTOMER, idCustomer));

        Optional<Customer> otherCustomer = customerRepository.findByEmail(currentCustomer.getEmail());
        if(otherCustomer.isPresent() && otherCustomer.get().getId() != customer.getId()) {
            throw new ExistsEmailException();
        } else {
            customer.setEmail(currentCustomer.getEmail());
        }

        customer.setFirstname(currentCustomer.getFirstname());
        customer.setLastname(currentCustomer.getLastname());
        customer.setDateOfBirth(currentCustomer.getDateOfBirth());
        customer.setPhoto(currentCustomer.getPhoto());
        customer.setRegion(currentCustomer.getRegion());
        return customer;
    }

    @Override
    @Transactional
    public void delete(Long idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(
                () -> new ResourceNotFoundException(CUSTOMER, idCustomer));
        customerRepository.delete(customer);
    }
}
