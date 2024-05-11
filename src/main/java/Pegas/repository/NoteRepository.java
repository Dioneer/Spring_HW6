package Pegas.repository;

import Pegas.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface NoteRepository extends JpaRepository<Note, Long>, NotesFilter, QuerydslPredicateExecutor<Note> {
}
