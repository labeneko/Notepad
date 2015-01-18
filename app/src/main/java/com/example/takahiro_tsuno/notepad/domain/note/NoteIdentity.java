package com.example.takahiro_tsuno.notepad.domain.note;

import com.example.takahiro_tsuno.notepad.domain.AbstractIdentity;

public class NoteIdentity extends AbstractIdentity<Long> {

    public static final NoteIdentity EMPTY = new NoteIdentity(-1L);

    public NoteIdentity(final Long value) {
        super(value);
    }
}
