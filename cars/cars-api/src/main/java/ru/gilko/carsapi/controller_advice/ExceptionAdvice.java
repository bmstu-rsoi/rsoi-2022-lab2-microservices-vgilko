package ru.gilko.carsapi.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.gilko.carsapi.exception.NoSuchEntityException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {NoSuchEntityException.class})
    ResponseEntity<?> notFoundException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
