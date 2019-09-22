package com.dashboard.api.dashboardAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dashboard.api.dashboardAPI.exception.ResourceNotFoundException;
import com.dashboard.api.dashboardAPI.model.Note;
import com.dashboard.api.dashboardAPI.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes(){
    	return noteRepository.findAll();
    }

    // Create a new Note

    @PostMapping("/notes")
    public Note createNote( @Valid @RequestBody Note note) {
        // System.out.println("############"+note.toString());
        // System.out.println("############"+note.getId());
        // System.out.println("############"+note.getTitle());
        // System.out.println("############"+note.getContent());
        // System.out.println("############"+note.getCreatedAt());
        // System.out.println("############"+note.getUpdatedAt());
    	return noteRepository.save(note);
    }

    
    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value="id") Long noteId) {
    	return noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public 	Note updateNote(@PathVariable(value="id") Long noteId, @Valid @RequestBody Note noteDetails) {
    	Note note=     	 noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Note", "id", noteId));
    	note.setTitle(noteDetails.getTitle());
    	note.setContent(noteDetails.getContent());
    	Note updatedNote= noteRepository.save(note);
    	return updatedNote;
    }

    // Delete a Note
    
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value="id") Long noteId, @Valid @RequestBody Note noteToDel) {
    	Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);
    	return ResponseEntity.ok().build();
    }
}
