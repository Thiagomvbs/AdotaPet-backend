package com.adocao.pet.api.exception;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
