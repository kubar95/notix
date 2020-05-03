package com.kubar95.notix.repository;

import com.kubar95.notix.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectReposotory extends JpaRepository<Subject, Long> {
    Iterable<Subject> findAllByOwnerId (Long ownerId);
    Subject getById(Long subjectId);
}
