package com.example.takahiro_tsuno.notepad;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotepadDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notepad.db";
    private static final int DATABASE_VERSION = 1;

    public NotepadDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        NoteModel.createTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        NoteModel.dropTable();

        onCreate(sqLiteDatabase);
    }
}
