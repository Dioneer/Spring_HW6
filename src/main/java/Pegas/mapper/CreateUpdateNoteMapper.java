package Pegas.mapper;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.entity.Note;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CreateUpdateNoteMapper implements Mapper<CreateUpdateNoteDto, Note>{
    @Override
    public Note map(CreateUpdateNoteDto create) {
        return Note.builder()
                .title(create.getTitle())
                .info(create.getInfo())
                .createdAt(create.getCreatedAt())
                .updateAt(create.getUpdateAt())
                .build();
    }

    @Override
    public Note map(CreateUpdateNoteDto update, Note note) {
        note.setTitle(update.getTitle());
        note.setInfo(update.getInfo());
        note.setCreatedAt(note.getCreatedAt());
        note.setUpdateAt(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));
        return note;
    }
}
