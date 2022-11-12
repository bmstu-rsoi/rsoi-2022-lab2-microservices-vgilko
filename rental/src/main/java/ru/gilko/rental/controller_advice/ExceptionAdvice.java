package ru.gilko.rental.controller_advice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.gilko.rental.exceptions.NoSuchEntityException;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler({NoSuchEntityException.class})
    public ResponseEntity<?> handleException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
