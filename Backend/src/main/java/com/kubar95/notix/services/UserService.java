package com.kubar95.notix.services;

import com.kubar95.notix.api.model.BaseUserDTO;
import com.kubar95.notix.api.model.JwtResponse;
import com.kubar95.notix.api.model.UserDTO;
import com.kubar95.notix.api.model.UserSignInDTO;
import com.kubar95.notix.exceptions.UserAlreadyInRepository;

public interface UserService {
    JwtResponse signIn(UserSignInDTO inDTO);

    BaseUserDTO signUp(UserDTO userDTO) throws UserAlreadyInRepository;
}
