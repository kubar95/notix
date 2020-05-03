package com.kubar95.notix.repository;

import com.kubar95.notix.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
