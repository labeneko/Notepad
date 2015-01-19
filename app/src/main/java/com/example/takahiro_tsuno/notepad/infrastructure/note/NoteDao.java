package com.example.takahiro_tsuno.notepad.infrastructure.note;

import android.content.ContentValues;
import android.database.Cursor;
import com.example.takahiro_tsuno.notepad.infrastructure.database.Database;
import com.example.takahiro_tsuno.notepad.infrastructure.database.dao.Dao;
import com.example.takahiro_tsuno.notepad.infrastructure.database.sqlite.SqliteCursorConverter;
import com.example.takahiro_tsuno.notepad.infrastructure.database.sqlite.SqliteDatabaseWrapper;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class NoteDao implements Dao<Note> {

    private SqliteDatabaseWrapper sqliteWrapper = new SqliteDatabaseWrapper(Database.open());
    private SqliteCursorConverter<Note> converter = new NoteCursorConverter();

    @Override
    public long count() {
        return sqliteWrapper.count(Note.TABLE);
    }

    @Override
    public boolean contains(final long id) {
        return sqliteWrapper.contains(Note.TABLE, id);
    }

    @Override
    public Note find(final long id) {
        Cursor cr = sqliteWrapper.find(Note.TABLE, id);
        if(cr == null || cr.getCount() == 0) {
            return null;
        }
        Note note = converter.convert(cr);
        cr.close();
        return note;
    }

    @Override
    public List<Note> findAll() {
        Cursor cr = sqliteWrapper.findAll(Note.TABLE);
        if(cr == null || cr.getCount() == 0) {
            return Collections.emptyList();
        }
        List<Note> notes = Lists.newArrayList();
        while(cr.moveToNext()) {
            notes.add(converter.convert(cr));
        }
        cr.close();
        return notes;
    }

    @Override
    public Note insert(final Note item) {
        long id = sqliteWrapper.insert(Note.TABLE, toContentValues(item, new Date(), false));
        if(id < 0) {
            return null;
        }
        return find(id);
    }

    @Override
    public Note update(final Note item) {
        if(item == null || !sqliteWrapper.contains(Note.TABLE, item.getId())) {
            return null;
        }
        suplementDateIfNull(item);
        long count = sqliteWrapper.update(Note.TABLE, toContentValues(item, new Date(), true), NoteTable.ID.getColumnName() + " = ?", item.getId());
        if(count < 0) {
            return null;
        }
        return find(item.getId());
    }

    @Override
    public Note updateOrInsert(final Note item) {
        if(sqliteWrapper.contains(Note.TABLE, item.getId())) {
            return update(item);
        } else {
            return insert(item);
        }
    }

    @Override
    public long remove(final long id) {
        if(!sqliteWrapper.contains(Note.TABLE, id)) {
            return 0;
        }
        return sqliteWrapper.delete(Note.TABLE, NoteTable.ID.getColumnName() + " = ?", id);
    }

    @Override
    public long remove(final Note item) {
        if(item == null) {
            return 0;
        }
        return remove(item.getId());
    }

    private void suplementDateIfNull(Note note) {
        Note cachedNote = find(note.getId());
        if(note.getCreatedAt() == null) {
            note.setCreatedAt(cachedNote.getCreatedAt());
        }
        if(note.getUpdatedAt() == null) {
            note.setUpdatedAt(cachedNote.getUpdatedAt());
        }
    }

    private static ContentValues toContentValues(Note note, Date now, boolean update) {
        String nowString = String.valueOf(now.getTime());
        String createdAtString = update ? String.valueOf(note.getCreatedAt().getTime()) : nowString;
        ContentValues contentValues = new ContentValues();
        if(update) {
            contentValues.put(NoteTable.ID.getColumnName(), note.getId());
        }
        contentValues.put(NoteTable.TITLE.getColumnName(), note.getTitle());
        contentValues.put(NoteTable.DESCRIPTION.getColumnName(), note.getDescription());
        contentValues.put(NoteTable.CREATED_AT.getColumnName(), createdAtString);
        contentValues.put(NoteTable.UPDATED_AT.getColumnName(), nowString);
        return contentValues;
    }
}
