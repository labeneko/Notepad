package com.example.takahiro_tsuno.notepad.domain.note;

import com.example.takahiro_tsuno.notepad.infrastructure.note.NoteDao;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

public class DefaultNoteRepository implements NoteRepository {

    private NoteDao noteDao = new NoteDao();
    private NoteEntityConverter converter = new NoteEntityConverter();

    @Override
    public Note find(final NoteIdentity noteIdentity) {
        if(noteIdentity == null) {
            return null;
        }
        return converter.toDomainEntity(noteDao.find(noteIdentity.getValue()));
    }

    @Override
    public List<Note> findAll() {
        return Lists.newArrayList(Lists.transform(noteDao.findAll(), new Function<com.example.takahiro_tsuno.notepad.infrastructure.note.Note, Note>() {
            @Override
            public Note apply(final com.example.takahiro_tsuno.notepad.infrastructure.note.Note input) {
                return converter.toDomainEntity(input);
            }
        }));
    }

    @Override
    public Note add(final Note note) {
        com.example.takahiro_tsuno.notepad.infrastructure.note.Note dbNote = noteDao.insert(converter.toDatabaseEntity(note));
        if(dbNote == null) {
            return null;
        }
        return converter.toDomainEntity(dbNote);
    }

    @Override
    public Note update(final Note note) {
        return converter.toDomainEntity(noteDao.update(converter.toDatabaseEntity(note)));
    }

    @Override
    public Note save(final Note note) {
        return converter.toDomainEntity(noteDao.updateOrInsert(converter.toDatabaseEntity(note)));
    }

    @Override
    public boolean remove(final NoteIdentity noteIdentity) {
        return noteIdentity != null && noteDao.remove(noteIdentity.getValue()) == 1;
    }

    @Override
    public boolean remove(final Note note) {
        return note != null && noteDao.remove(converter.toDatabaseEntity(note)) == 1;
    }

    @Override
    public boolean contains(final Note note) {
        return note != null && noteDao.contains(note.getIdentity().getValue());
    }
}
