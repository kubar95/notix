package com.kubar95.notix.api.model;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDetailsDTO {
    private String name;
    private List<NoteDTO> notes;
}
