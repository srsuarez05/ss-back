package com.project.ssback.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.project.ssback.utilities.Constants.RESOURCE_NOT_FOUND_EXCEPTION;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String nameResource;
    private long valueField;

    public ResourceNotFoundException(String nameResource, long valueField) {
        super(String.format(RESOURCE_NOT_FOUND_EXCEPTION, nameResource, valueField));
        this.nameResource = nameResource;
        this.valueField = valueField;
    }

}
