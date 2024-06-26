package Pegas.service;

import Pegas.dto.CreateUpdateNoteDto;
import Pegas.dto.FilterNoteDto;
import Pegas.dto.QPredicateNote;
import Pegas.dto.ReadNoteDto;
import Pegas.entity.Note;
import Pegas.mapper.CreateUpdateNoteMapper;
import Pegas.mapper.ReadNoteMapper;
import Pegas.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static Pegas.entity.QNote.note;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;
    private final CreateUpdateNoteMapper createUpdateNoteMapper;
    private final ReadNoteMapper readNoteMapper;


    @Override
    public List<ReadNoteDto> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(readNoteMapper::map)
                .toList();
    }

    @Override
    public List<ReadNoteDto> getAllNotes(FilterNoteDto filterNoteDto, Pageable pageable) {
        var predicate = QPredicateNote.builder()
                .add(filterNoteDto.getTitle(), note.title::containsIgnoreCase)
                .add(filterNoteDto.getCreatedAt(), note.createdAt::before)
                .add(filterNoteDto.getUpdateAt(), note.updateAt::before)
                .build();
        return noteRepository.findAll(predicate, pageable).stream()
                .map(readNoteMapper::map)
                .toList();
    }

    @Override
    public Optional<ReadNoteDto> getNoteById(Long id) {
        return noteRepository.findById(id).map(readNoteMapper::map);
    }

    @Override
    @Transactional
    public boolean deleteNote(Long id) {
        return noteRepository.findById(id).map(i-> {
                    noteRepository.delete(i);
                    noteRepository.flush();
                    return true;
                }).orElse(false);
    }

    @Override
    @Transactional
    public ReadNoteDto createNote(CreateUpdateNoteDto createUpdateNoteDto) {
        return Optional.of(createUpdateNoteDto).map(createUpdateNoteMapper::map)
                .map(noteRepository::save).map(readNoteMapper::map).orElseThrow();
    }

    @Override
    @Transactional
    public ReadNoteDto updateNote(Long id, CreateUpdateNoteDto createUpdateNoteDto) {
        return noteRepository.findById(id).map(i-> createUpdateNoteMapper.map(createUpdateNoteDto, i))
                .map(readNoteMapper::map).orElseThrow();
    }
}
