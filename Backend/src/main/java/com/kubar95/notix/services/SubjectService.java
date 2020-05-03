package com.kubar95.notix.services;

import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.api.model.SubjectDTO;
import com.kubar95.notix.api.model.SubjectDetailsDTO;
import com.kubar95.notix.exceptions.AccessDenied;
import com.kubar95.notix.exceptions.ResourceNotFound;

import java.util.List;

public interface SubjectService {

    List<SubjectDTO> getAllSubjects ();

    void deleteSubjectById(Long subjectId);

    SubjectDTO createSubject(SubjectDTO subjectDTO);


    SubjectDetailsDTO getSubjectById(Long id) throws ResourceNotFound, AccessDenied;

    NoteDTO createOrUpdateNote(Long subjectId, NoteDTO noteDTO);
}
