package com.example.takahiro_tsuno.notepad.infrastructure.note;

import android.database.Cursor;
import com.example.takahiro_tsuno.notepad.infrastructure.database.sqlite.SqliteCursorConverter;

import java.util.Date;

public class NoteCursorConverter implements SqliteCursorConverter<Note> {

    @Override
    public Note convert(final Cursor cursor) {
        Note note = new Note();
        note.setId(getId(cursor));
        note.setTitle(getTitle(cursor));
        note.setDescription(getDescription(cursor));
        note.setCreatedAt(getCreatedAt(cursor));
        note.setUpdatedAt(getUpdatedAt(cursor));
        return note;
    }

    private int getId(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(NoteTable.ID.getColumnName()));
    }

    private String getTitle(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(NoteTable.TITLE.getColumnName()));
    }

    private String getDescription(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(NoteTable.DESCRIPTION.getColumnName()));
    }

    private Date getCreatedAt(Cursor cursor) {
        return new Date(cursor.getLong(cursor.getColumnIndex(NoteTable.CREATED_AT.getColumnName())));
    }

    private Date getUpdatedAt(Cursor cursor) {
        return new Date(cursor.getLong(cursor.getColumnIndex(NoteTable.UPDATED_AT.getColumnName())));
    }
}
