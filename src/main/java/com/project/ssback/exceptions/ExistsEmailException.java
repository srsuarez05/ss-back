package com.project.ssback.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.project.ssback.utilities.Constants.EXISTS_EMAIL_EXCEPTION;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistsEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExistsEmailException() {
        super(String.format(EXISTS_EMAIL_EXCEPTION));
    }
}
