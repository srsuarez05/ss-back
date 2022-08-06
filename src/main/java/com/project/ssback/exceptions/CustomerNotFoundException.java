package com.project.ssback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.project.ssback.utilities.Constants.CUSTOMER_NOT_FOUND_EXCEPTION;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long idCustomer) {
        super(CUSTOMER_NOT_FOUND_EXCEPTION + idCustomer);
    }
}
