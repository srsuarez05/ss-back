package com.project.ssback.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus state;
    private String message;
}
