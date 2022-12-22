package ru.gilko.paymentapi.exception;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String s) {
        super(s);
    }
}
