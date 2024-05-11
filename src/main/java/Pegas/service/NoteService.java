package Pegas.service;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.dto.FilterNoteDto;
import Pegas.dto.ReadNoteDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoteService {
        List<ReadNoteDto> getAllNotes();
        List<ReadNoteDto> getAllNotes(FilterNoteDto filterNoteDto, Pageable pageable);
        Optional<ReadNoteDto> getNoteById(Long id);
        boolean deleteNote(Long id);
        ReadNoteDto createNote(CreateUpdateNoteDto createUpdateNoteDto);
        ReadNoteDto updateNote(Long id, CreateUpdateNoteDto createUpdateNoteDto);

}
