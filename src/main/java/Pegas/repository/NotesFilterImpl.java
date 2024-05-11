package Pegas.repository;

import Pegas.dto.FilterNoteDto;
import Pegas.dto.QPredicateNote;
import Pegas.entity.Note;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static Pegas.entity.QNote.note;

@RequiredArgsConstructor
public class NotesFilterImpl implements NotesFilter {
    private final EntityManager entityManager;
    @Override
    public List<Note> findAllByFilter(FilterNoteDto filterNoteDto) {
            var predicate = QPredicateNote.builder()
                    .add(filterNoteDto.getTitle(), note.title::containsIgnoreCase)
                    .add(filterNoteDto.getCreatedAt(), note.createdAt::before)
                    .add(filterNoteDto.getUpdateAt(), note.updateAt::before)
                    .build();
            return new JPAQuery<Note>(entityManager)
                    .select(note)
                    .from(note)
                    .where(predicate)
                    .fetch();
    }
}
