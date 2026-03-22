package com.example.notesapp.service;

import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public List<Note> active() {
        return repo.findByArchivedFalseAndDeletedFalse();
    }

    public List<Note> archive() {
        return repo.findByArchivedTrueAndDeletedFalse();
    }

    public List<Note> bin() {
        return repo.findByDeletedTrue();
    }

    public void save(Note note) {
        repo.save(note);
    }

    public void archive(Long id) {
        Note n = repo.findById(id).orElseThrow();
        n.setArchived(true);
        repo.save(n);
    }

    public void delete(Long id) {
        Note n = repo.findById(id).orElseThrow();
        n.setDeleted(true);
        repo.save(n);
    }

    public Note getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
    public void unarchive(Long id) {
        Note n = repo.findById(id).orElseThrow();
        n.setArchived(false);
        repo.save(n);
    }

    public void permanentDelete(Long id) {
        repo.deleteById(id);
    }

    public void restore(Long id) {
        Note n = repo.findById(id).orElseThrow();
        n.setDeleted(false);
        repo.save(n);
    }
}