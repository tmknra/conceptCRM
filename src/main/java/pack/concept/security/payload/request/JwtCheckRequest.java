package pack.concept.security.payload.request;

import javax.validation.constraints.NotNull;

public class JwtCheckRequest {

    @NotNull
    private String token;

    public String getToken() {
        return token;
    }
}
