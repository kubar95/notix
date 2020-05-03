package com.kubar95.notix.api.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NoteDTO {
    private Long id;
    private String title;
    private Timestamp date;
    private String content;
}
