package com.kubar95.notix.services;

import com.kubar95.notix.api.model.NoteDTO;

public interface NoteService {
    NoteDTO createOrUpdateNote(NoteDTO note);
}
