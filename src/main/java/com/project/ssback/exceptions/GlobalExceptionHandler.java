package com.project.ssback.exceptions;

import com.project.ssback.dto.ErrorDetailsDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> manageResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> manageGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetailsDto> manageBlogException(CustomException exception, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nameField = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(nameField, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
