package Pegas.controller;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.dto.ReadNoteDto;
import Pegas.entity.Note;
import Pegas.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<ReadNoteDto>> getAll() {
        try {
            return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ReadNoteDto> createNote(@RequestBody CreateUpdateNoteDto create) {
        try {
            return new ResponseEntity<>(noteService.createNote(create), HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReadNoteDto(null, "", "",null,null));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadNoteDto> getNoteById(@PathVariable("id") Long id) {
        ReadNoteDto productById;
        try {
            productById = noteService.getNoteById(id).orElseThrow();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReadNoteDto(null, "", "",null,null));
        }
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<ReadNoteDto> updateProduct(@PathVariable("id") Long id, @RequestBody CreateUpdateNoteDto note) {
        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
