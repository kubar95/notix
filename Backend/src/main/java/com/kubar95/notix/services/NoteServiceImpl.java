package com.kubar95.notix.services;

import com.kubar95.notix.api.mapper.NoteMapper;
import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteDTO createOrUpdateNote(NoteDTO note) {
        return null;
    }
}
