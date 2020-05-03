package com.kubar95.notix.services;

import com.kubar95.notix.api.mapper.NoteMapper;
import com.kubar95.notix.api.mapper.SubjectMapper;
import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.api.model.SubjectDTO;
import com.kubar95.notix.api.model.SubjectDetailsDTO;
import com.kubar95.notix.domain.Note;
import com.kubar95.notix.domain.Subject;
import com.kubar95.notix.domain.User;
import com.kubar95.notix.exceptions.AccessDenied;
import com.kubar95.notix.exceptions.ResourceNotFound;
import com.kubar95.notix.repository.NoteRepository;
import com.kubar95.notix.repository.SubjectReposotory;
import com.kubar95.notix.repository.UserRepository;
import com.kubar95.notix.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final UserRepository userRepository;
    private final SubjectReposotory subjectReposotory;
    private final SecurityService securityService;
    private final SubjectMapper subjectMapper;
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public List<SubjectDTO> getAllSubjects() {
        Long authenticatedUserId = securityService.getUserId();
        User authenticatedUser = userRepository.getById(authenticatedUserId);
        return authenticatedUser.getSubjects()
                .stream()
                .map(subjectMapper::subjectToSubjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubjectById(Long subjectId) {
        subjectReposotory.deleteById(subjectId);
    }

    @Override
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Long authenticatedUserId = securityService.getUserId();
        User owner = userRepository.getById(authenticatedUserId);
        Subject subject = subjectMapper.subjectDtoToSubject(subjectDTO);
        subject.setOwner(owner);
        Subject savedSubject = subjectReposotory.save(subject);
        return subjectMapper.subjectToSubjectDto(savedSubject);
    }

    @Override
    public SubjectDetailsDTO getSubjectById(Long id) throws ResourceNotFound, AccessDenied {
        Subject subject = subjectReposotory.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Sbuject not found"));

        if(!securityService.hasAccessToEntity(subject)){
            throw new AccessDenied("Access Denied to subject entity");
        }

        List<NoteDTO> notesDto = subject.getNotes().stream()
                .map(noteMapper::noteToNoteDto)
                .collect(Collectors.toList());

        return subjectMapper.subjectToSubjectDetailsDTO(subject,notesDto);
    }

    @Override
    public NoteDTO createOrUpdateNote(Long subjectId, NoteDTO noteDTO) {
        Subject currentSubject = subjectReposotory.getById(subjectId);
        Note note = noteMapper.noteDtoToNote(noteDTO);
        note.setSubject(currentSubject);

        Note savedNote = noteRepository.save(note);

        return noteMapper.noteToNoteDto(savedNote);
    }

}
