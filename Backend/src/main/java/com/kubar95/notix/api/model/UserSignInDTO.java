package com.kubar95.notix.api.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserSignInDTO {
    private String username;
    private String password;

}
