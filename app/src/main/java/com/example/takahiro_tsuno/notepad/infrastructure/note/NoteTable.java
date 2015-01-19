package com.example.takahiro_tsuno.notepad.infrastructure.note;

import com.example.takahiro_tsuno.notepad.infrastructure.database.Column;
import com.example.takahiro_tsuno.notepad.infrastructure.database.Table;

import java.util.Arrays;
import java.util.List;

public class NoteTable implements Table {

    private static final String TABLE_NAME = "notes";
    public static final Column TITLE = new Column() {
        @Override
        public String getColumnName() {
            return "title";
        }

        @Override
        public String getAttribute() {
            return "TEXT NOT NULL";
        }
    };
    public static final Column DESCRIPTION = new Column() {
        @Override
        public String getColumnName() {
            return "description";
        }

        @Override
        public String getAttribute() {
            return "TEXT";
        }
    };

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Column> getColumns() {
        return Arrays.asList(ID, TITLE, DESCRIPTION, CREATED_AT, UPDATED_AT);
    }
}
