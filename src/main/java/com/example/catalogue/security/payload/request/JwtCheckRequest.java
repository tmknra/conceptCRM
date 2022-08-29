package com.example.catalogue.security.payload.request;

import javax.validation.constraints.NotNull;

public class JwtCheckRequest {

    @NotNull
    private String token;

    public String getToken() {
        return token;
    }
}
