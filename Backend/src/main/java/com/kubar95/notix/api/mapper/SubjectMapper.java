package com.kubar95.notix.api.mapper;

import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.api.model.SubjectDTO;

import com.kubar95.notix.api.model.SubjectDetailsDTO;
import com.kubar95.notix.domain.Subject;
import org.aspectj.weaver.ast.Not;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDTO subjectToSubjectDto(Subject subject);
    Subject subjectDtoToSubject (SubjectDTO subjectDTO);

    @Mapping(source = "subject.name", target = "name")
    @Mapping (source = "notes", target = "notes")
    SubjectDetailsDTO subjectToSubjectDetailsDTO (Subject subject, List<NoteDTO> notes);
}
