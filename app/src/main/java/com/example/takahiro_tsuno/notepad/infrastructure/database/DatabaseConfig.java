package com.example.takahiro_tsuno.notepad.infrastructure.database;

import android.content.Context;
import com.example.takahiro_tsuno.notepad.R;
import com.example.takahiro_tsuno.notepad.infrastructure.note.Note;

import java.util.Arrays;
import java.util.List;

public class DatabaseConfig {

    private static List<Table> TABLES = Arrays.asList(Note.TABLE);
    private Context context;

    public DatabaseConfig(Context context) {
        this.context = context;
    }

    public String getDatabaseName() {
        return context.getString(R.string.database_name);
    }

    public int getDatabaseVersion() {
        return context.getResources().getInteger(R.integer.database_version);
    }

    public List<Table> getTables() {
        return TABLES;
    }

    public Context getContext() {
        return context;
    }
}
