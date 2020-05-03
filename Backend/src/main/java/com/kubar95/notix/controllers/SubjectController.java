package com.kubar95.notix.controllers;

import com.kubar95.notix.api.model.NoteDTO;
import com.kubar95.notix.api.model.SubjectDTO;
import com.kubar95.notix.api.model.SubjectDetailsDTO;
import com.kubar95.notix.exceptions.AccessDenied;
import com.kubar95.notix.exceptions.ResourceNotFound;
import com.kubar95.notix.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public SubjectDTO createSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.createSubject(subjectDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubjectById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubjectDetailsDTO getSubjectById(@PathVariable Long id) {
        try {
            return subjectService.getSubjectById(id);
        } catch (ResourceNotFound resourceNotFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, resourceNotFound.getMessage(), resourceNotFound);
        } catch (AccessDenied accessDenied) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, accessDenied.getMessage(), accessDenied);
        }
    }

    @PutMapping("/{id}/note")
    public NoteDTO createOrUpdateNote (@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        return subjectService.createOrUpdateNote(id,noteDTO);
    }

}
