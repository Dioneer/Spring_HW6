package Pegas.mapper;

import Pegas.dto.ReadNoteDto;
import Pegas.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class ReadNoteMapper implements Mapper<Note, ReadNoteDto>{
    @Override
    public ReadNoteDto map(Note note) {
        return new ReadNoteDto(note.getId(), note.getTitle(), note.getInfo(), note.getCreatedAt(), note.getUpdateAt());
    }
}
