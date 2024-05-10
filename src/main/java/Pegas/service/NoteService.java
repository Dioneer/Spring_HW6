package Pegas.service;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.dto.ReadNoteDto;

import java.util.List;
import java.util.Optional;

public interface NoteService {
        List<ReadNoteDto> getAllNotes();
        Optional<ReadNoteDto> getNoteById(Long id);
        boolean deleteNote(Long id);
        ReadNoteDto createNote(CreateUpdateNoteDto createUpdateNoteDto);
        ReadNoteDto updateNote(Long id, CreateUpdateNoteDto createUpdateNoteDto);

}
