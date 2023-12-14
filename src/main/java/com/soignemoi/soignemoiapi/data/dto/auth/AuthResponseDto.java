package com.soignemoi.soignemoiapi.data.dto.auth;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer ";
    private String role;
    private long validity;

    public AuthResponseDto(String accessToken, String role, long validity) {
        this.accessToken = accessToken;
        this.role = role;
        this.validity = validity;
    }

}
