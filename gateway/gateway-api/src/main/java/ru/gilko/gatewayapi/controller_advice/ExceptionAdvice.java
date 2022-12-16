package ru.gilko.gatewayapi.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.gilko.gatewayapi.exception.InvalidOperationException;
import ru.gilko.gatewayapi.exception.NoSuchEntityException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {InvalidOperationException.class})
    public ResponseEntity<?> badRequest(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchEntityException.class})
    public ResponseEntity<?> notFound(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
