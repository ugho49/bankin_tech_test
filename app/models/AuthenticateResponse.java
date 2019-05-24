package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class AuthenticateResponse {

    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("expires_at")
    public Instant expiresAt;
}
