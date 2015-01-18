package com.example.takahiro_tsuno.notepad.infrastructure.database.sqlite;

import android.database.Cursor;
import com.example.takahiro_tsuno.notepad.infrastructure.database.Entity;

public interface SqliteCursorConverter<E extends Entity> {

    public E convert(Cursor cursor);
}
