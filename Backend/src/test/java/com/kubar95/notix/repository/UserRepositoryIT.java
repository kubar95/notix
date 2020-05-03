package com.kubar95.notix.repository;


import com.kubar95.notix.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findAllTest() {
        Set<User> users = new HashSet<>();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRepository.findAll().forEach(users::add);

        User testUser = users.iterator().next();

        assertEquals(1, users.size());
        assertEquals("test", testUser.getUsername());
        assertTrue(passwordEncoder.matches("test", testUser.getPassword()));
    }

}