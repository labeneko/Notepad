package com.example.takahiro_tsuno.notepad.domain.note;

import com.example.takahiro_tsuno.notepad.domain.AbstractValueObject;

public class NoteMetaInfo extends AbstractValueObject {

    private String title;
    private String description;

    public NoteMetaInfo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
