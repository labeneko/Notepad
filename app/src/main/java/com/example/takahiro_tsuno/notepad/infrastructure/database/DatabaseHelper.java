package com.example.takahiro_tsuno.notepad.infrastructure.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.takahiro_tsuno.notepad.infrastructure.database.sqlite.SqliteDatabaseWrapper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private DatabaseConfig config;

    public DatabaseHelper(DatabaseConfig config) {
        super(config.getContext(), config.getDatabaseName(), null, config.getDatabaseVersion());
        this.config = config;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        SqliteDatabaseWrapper sqliteWrapper = new SqliteDatabaseWrapper(db);
        for(Table table : config.getTables()) {
            sqliteWrapper.create(table);
        }
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    }
}
