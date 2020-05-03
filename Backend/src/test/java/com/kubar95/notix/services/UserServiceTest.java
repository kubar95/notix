package com.kubar95.notix.services;

import com.kubar95.notix.api.mapper.UserMapper;
import com.kubar95.notix.api.model.BaseUserDTO;
import com.kubar95.notix.api.model.UserDTO;
import com.kubar95.notix.domain.User;
import com.kubar95.notix.exceptions.UserAlreadyInRepository;
import com.kubar95.notix.repository.UserRepository;
import com.kubar95.notix.security.JwtTokenProvider;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;


    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(
                authenticationManager,
                new JwtTokenProvider(),
                userRepository,
                passwordEncoder,
                UserMapper.INSTANCE
        );
    }

    @Test
    void signUpTest() throws UserAlreadyInRepository {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(USERNAME);
        userDTO.setPassword(PASSWORD);
        userDTO.setEmail(EMAIL);

        User savedUser = User.builder().username(USERNAME).email(EMAIL).build();

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        //when
        BaseUserDTO savedDto = userService.signUp(userDTO);

        //then
        assertEquals(userDTO.getUsername(), savedDto.getUsername());
        assertEquals(userDTO.getEmail(), savedDto.getEmail());

    }

    @Test
    void signUpUsernameExistsTest() {

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.signUp(any(UserDTO.class)));
    }

    @Test
    void signUpEmailExistsTest() {

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.signUp(any(UserDTO.class)));
    }
}