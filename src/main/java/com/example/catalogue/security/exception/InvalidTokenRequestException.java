package com.example.catalogue.security.exception;

public class InvalidTokenRequestException extends Throwable {
    private static final long serialVersionUID = 1L;

    public InvalidTokenRequestException(String tokenType, String token, String message) {
        super(String.format("%s: [%s] token: [%s] ", message, tokenType, token));
    }
}
