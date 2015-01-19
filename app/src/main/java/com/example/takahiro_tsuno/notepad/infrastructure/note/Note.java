package com.example.takahiro_tsuno.notepad.infrastructure.note;

import com.example.takahiro_tsuno.notepad.infrastructure.database.AbstractEntity;
import com.example.takahiro_tsuno.notepad.infrastructure.database.Table;

import java.util.Date;

public class Note extends AbstractEntity {

    public static Table TABLE = new NoteTable();
    private String title;
    private String description;

    public Note() {
    }

    public Note(final String title, final String description) {
        init(title, description);
    }

    public Note(final String title, final String description, final Date createdAt, final Date updatedAt) {
        super(createdAt, updatedAt);
        init(title, description);
    }

    public Note(final long id, final String title, final String description, final Date createdAt, final Date updatedAt) {
        super(id, createdAt, updatedAt);
        init(title, description);
    }

    private void init(final String title, final String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
