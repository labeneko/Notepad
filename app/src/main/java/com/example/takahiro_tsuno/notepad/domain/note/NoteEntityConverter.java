package com.example.takahiro_tsuno.notepad.domain.note;

import com.example.takahiro_tsuno.notepad.domain.EntityConverter;

public class NoteEntityConverter implements EntityConverter<Note, com.example.takahiro_tsuno.notepad.infrastructure.note.Note> {

    @Override
    public Note toDomainEntity(final com.example.takahiro_tsuno.notepad.infrastructure.note.Note dbEntity) {
        Note note = new Note(new NoteIdentity(dbEntity.getId()));
        note.setMetaInfo(new NoteMetaInfo(dbEntity.getTitle(), dbEntity.getDescription()));
        return note;
    }

    @Override
    public com.example.takahiro_tsuno.notepad.infrastructure.note.Note toDatabaseEntity(final Note domainEntity) {
        com.example.takahiro_tsuno.notepad.infrastructure.note.Note note = new com.example.takahiro_tsuno.notepad.infrastructure.note.Note();
        note.setId(domainEntity.getIdentity().getValue());
        note.setTitle(domainEntity.getTitle());
        note.setDescription(domainEntity.getDescription());
        return note;
    }
}
