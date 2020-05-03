package com.kubar95.notix.controllers;

import com.kubar95.notix.api.model.BaseUserDTO;
import com.kubar95.notix.api.model.JwtResponse;
import com.kubar95.notix.api.model.UserDTO;
import com.kubar95.notix.api.model.UserSignInDTO;
import com.kubar95.notix.exceptions.UserAlreadyInRepository;
import com.kubar95.notix.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public JwtResponse singIn (@RequestBody UserSignInDTO userSignInDTO) {
        return userService.signIn(userSignInDTO);
    }

    @PostMapping("/signup")
    public BaseUserDTO singUp (@RequestBody UserDTO userDTO) {
        try {
            return userService.signUp(userDTO);
        } catch (UserAlreadyInRepository ex) {
            throw  new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex );
        }
    }


}
