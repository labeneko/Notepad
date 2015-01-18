package com.example.takahiro_tsuno.notepad.domain.note;

import com.example.takahiro_tsuno.notepad.domain.AbstractEntity;

public class Note extends AbstractEntity<NoteIdentity, Note> {

    private NoteMetaInfo metaInfo;

    public Note(final NoteIdentity identity) {
        super(identity);
    }

    public NoteMetaInfo getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(NoteMetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getTitle() {
        return metaInfo != null ? metaInfo.getTitle() : null;
    }

    public String getDescription() {
        return metaInfo != null ? metaInfo.getDescription() : null;
    }
}
