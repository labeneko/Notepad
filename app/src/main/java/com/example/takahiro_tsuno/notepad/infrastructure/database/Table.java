package com.example.takahiro_tsuno.notepad.infrastructure.database;

import java.util.List;

public interface Table {

    public static final Column ID = new Column() {
        @Override
        public String getColumnName() {
            return "id";
        }

        @Override
        public String getAttribute() {
            return "INTEGER PRIMARY KEY AUTOINCREMENT";
        }
    };
    public static final Column CREATED_AT = new Column() {
        @Override
        public String getColumnName() {
            return "created_at";
        }

        @Override
        public String getAttribute() {
            return "INTEGER NOT NULL";
        }
    };
    public static final Column UPDATED_AT = new Column() {
        @Override
        public String getColumnName() {
            return "updated_at";
        }

        @Override
        public String getAttribute() {
            return "INTEGER NOT NULL";
        }
    };

    public String getTableName();

    public List<Column> getColumns();
}
