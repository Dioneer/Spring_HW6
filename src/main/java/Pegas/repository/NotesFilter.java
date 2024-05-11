package Pegas.repository;

import Pegas.dto.FilterNoteDto;
import Pegas.entity.Note;
import java.util.List;

public interface NotesFilter {
    List<Note> findAllByFilter(FilterNoteDto filterNoteDto);
}
