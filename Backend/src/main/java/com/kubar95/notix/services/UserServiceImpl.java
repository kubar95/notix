package com.kubar95.notix.services;

import com.kubar95.notix.api.mapper.UserMapper;
import com.kubar95.notix.api.model.BaseUserDTO;
import com.kubar95.notix.api.model.JwtResponse;
import com.kubar95.notix.api.model.UserDTO;
import com.kubar95.notix.api.model.UserSignInDTO;
import com.kubar95.notix.domain.User;
import com.kubar95.notix.exceptions.UserAlreadyInRepository;
import com.kubar95.notix.repository.UserRepository;
import com.kubar95.notix.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public JwtResponse signIn(UserSignInDTO inDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        inDTO.getUsername(),
                        inDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.genereateToken(authentication);
        Long expiration = jwtTokenProvider.getExpirationFromJWT(jwt).getTime();
        return new JwtResponse(jwt, expiration);
    }

    @Override
    public BaseUserDTO signUp (UserDTO userDTO) throws UserAlreadyInRepository  {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyInRepository("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyInRepository("Email already exists");
        }

        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = userMapper.userDtoToUser(userDTO,encryptedPassword);

        User savedUser = userRepository.save(user);

        return userMapper.userToBaseUserDto(savedUser);



    }
}
