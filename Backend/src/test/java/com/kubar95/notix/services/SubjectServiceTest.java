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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {



    @Mock
    UserRepository userRepository;

    @Mock
    SubjectReposotory subjectReposotory;

    @Mock
    NoteRepository noteRepository;

    @Mock
    SecurityService securityService;


    SubjectService subjectService;
    @BeforeEach
    void init() {
        subjectService = new SubjectServiceImpl(
                userRepository,
                subjectReposotory,
                securityService,
                SubjectMapper.INSTANCE,
                noteRepository,
                NoteMapper.INSTANCE);
    }

    @Test
    void getAllSubjects() {
        //given
        Subject subject1 = new Subject();
        subject1.setId(1L);
        Subject subject2 = new Subject();
        subject2.setId(2L);

        User user = new User();
        user.addSubject(subject1);
        user.addSubject(subject2);

        when(userRepository.getById(anyLong())).thenReturn(user);

        //when
        List<SubjectDTO> subjectDTOS = subjectService.getAllSubjects();

        assertEquals(2, subjectDTOS.size());

        verify(userRepository, times(1)).getById(anyLong());

    }

    @Test
    void getSubjectById() throws ResourceNotFound, AccessDenied {
        //given
        Subject subject = new Subject();
        subject.setId(1L);

        Note note1 = new Note();
        note1.setId(1L);
        note1.setTitle("note1");

        Note note2 = new Note();
        note2.setId(2L);
        note2.setTitle("note2");

        subject.setNotes(Arrays.asList(note1, note2));

        when(subjectReposotory.findById(anyLong())).thenReturn(Optional.of(subject));
        when(securityService.hasAccessToEntity(any(Subject.class))).thenReturn(true);

        //when
        SubjectDetailsDTO subjectDetailsDTO = subjectService.getSubjectById(anyLong());

        List<NoteDTO> notesFromSubjectDetails = subjectDetailsDTO.getNotes();

        //then
        assertNotNull(subjectDetailsDTO);
        assertEquals(2, notesFromSubjectDetails.size());
        assertEquals("note1", notesFromSubjectDetails.get(0).getTitle());
        assertEquals("note2", notesFromSubjectDetails.get(1).getTitle());
    }

    @Test
    void getSubjectByIdNotFound() {
        when(subjectReposotory.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> {
            subjectService.getSubjectById(anyLong());
        });
    }

    @Test
    void getSubjectByIdAccessDenied() {
        Subject subject = new Subject();
        Optional<Subject> subjectOptional = Optional.of(subject);
        when (subjectReposotory.findById(anyLong())).thenReturn(subjectOptional);
        when (securityService.hasAccessToEntity(any(Subject.class))).thenReturn(false);

        assertThrows(AccessDenied.class, () -> subjectService.getSubjectById(anyLong()));
    }
}