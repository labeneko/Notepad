package com.example.takahiro_tsuno.notepad.domain.note;

import com.example.takahiro_tsuno.notepad.infrastructure.note.NoteDao;

import java.util.List;

public class DefaultNoteRepository implements NoteRepository {

    private NoteDao noteDao = new NoteDao();

    @Override
    public Note find(final NoteIdentity noteIdentity) {
        return null;
    }

    @Override
    public List<Note> findAll() {
        return null;
    }

    @Override
    public Note add(final Note note) {
        return null;
    }

    @Override
    public Note update(final Note note) {
        return null;
    }

    @Override
    public Note save(final Note note) {
        return null;
    }

    @Override
    public boolean remove(final NoteIdentity noteIdentity) {
        return false;
    }

    @Override
    public boolean remove(final Note note) {
        return false;
    }

    @Override
    public boolean contains(final Note note) {
        return false;
    }
}
