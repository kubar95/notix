package com.kubar95.notix.controllers;

import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PutMapping
    private NoteDTO createOrUpdateNote(@RequestBody NoteDTO note) {
        return noteService.createOrUpdateNote(note);
    }

}
