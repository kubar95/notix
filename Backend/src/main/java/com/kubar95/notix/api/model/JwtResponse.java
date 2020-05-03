package com.kubar95.notix.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private final String tokenType = "Bearer";
    private Long expiration;


}
