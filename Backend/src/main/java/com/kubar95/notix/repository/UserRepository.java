package com.kubar95.notix.repository;

import com.kubar95.notix.domain.Event;
import com.kubar95.notix.domain.Subject;
import com.kubar95.notix.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername (String username);
    Boolean existsByUsername (String username);
    Boolean existsByEmail (String email);
    User getById(Long id);
}
