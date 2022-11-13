package ru.gilko.payment.exception;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String s) {
        super(s);
    }
}
