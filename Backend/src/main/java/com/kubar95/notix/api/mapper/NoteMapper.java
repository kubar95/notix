package com.kubar95.notix.api.mapper;

import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.domain.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    NoteDTO noteToNoteDto (Note note);
    Note noteDtoToNote (NoteDTO noteDTO);
}
