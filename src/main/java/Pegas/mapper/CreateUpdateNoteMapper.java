package Pegas.mapper;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class CreateUpdateNoteMapper implements Mapper<CreateUpdateNoteDto, Note>{
    @Override
    public Note map(CreateUpdateNoteDto create) {
        return Note.builder()
                .title(create.getTitle())
                .body(create.getBody())
                .createdAt(create.getCreatedAt())
                .updateAt(create.getUpdateAt())
                .build();
    }

    @Override
    public Note map(CreateUpdateNoteDto update, Note note) {
        note.setTitle(update.getTitle());
        note.setBody(update.getBody());
        note.setCreatedAt(update.getCreatedAt());
        note.setUpdateAt(update.getUpdateAt());
        return note;
    }
}
