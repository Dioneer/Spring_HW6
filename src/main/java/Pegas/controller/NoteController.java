package Pegas.controller;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.dto.FilterNoteDto;
import Pegas.dto.ReadNoteDto;
import Pegas.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrControllerAnswer(HttpStatus.NOT_FOUND.value(), "No notes were found"),
                    HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<?> getAllByFilter(@RequestBody @Validated FilterNoteDto filter, Pageable pageable) {
        try {
            return new ResponseEntity<>(noteService.getAllNotes(filter, pageable), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrControllerAnswer(HttpStatus.NOT_FOUND.value(), "No notes were found"),
                    HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody @Validated CreateUpdateNoteDto create) {
        try {
            return new ResponseEntity<>(noteService.createNote(create), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrControllerAnswer(HttpStatus.BAD_REQUEST.value(), "Some fields in your request" +
                    "were not correct"), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable("id") Long id) {
        ReadNoteDto productById;
        try {
            productById = noteService.getNoteById(id).orElseThrow();
        } catch (RuntimeException e){
            return new ResponseEntity<>(new ErrControllerAnswer(HttpStatus.NOT_FOUND.value(), "No notes with id " +
                    id + " was found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody CreateUpdateNoteDto note) {
        try {
            return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
        }catch (RuntimeException e){
                return new ResponseEntity<>(new ErrControllerAnswer(HttpStatus.BAD_REQUEST.value(), "Some fields in your request" +
                        "were not correct"),HttpStatus.BAD_REQUEST);
            }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        if(noteService.deleteNote(id)){
            return ResponseEntity.ok().build();
        }else{
            return new ResponseEntity<>(new ErrControllerAnswer(HttpStatus.NOT_FOUND.value(), "No notes with id " +
                    id + " was found"),HttpStatus.NOT_FOUND);
        }
    }
}
