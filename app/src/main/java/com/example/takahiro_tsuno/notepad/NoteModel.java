package com.example.takahiro_tsuno.notepad;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteModel {

    NotepadDbHelper notepadDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public NoteModel(Context context) {
        notepadDbHelper = new NotepadDbHelper(context);
        //DB
        sqLiteDatabase = notepadDbHelper.getReadableDatabase();
    }

    public List<Note> getList(){
        List<Note> noteList = new ArrayList<Note>();

        Cursor cursor = sqLiteDatabase.query(
                "note",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        //ArrayListにコピー（なんか無駄）
        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String currentDate = cursor.getString(cursor.getColumnIndex("current_date"));

            Note note = new Note(id, currentDate);

            noteList.add(note);
        }

        //戻す
        return noteList;
    }

    public boolean set(String currentDate){
        ContentValues contentValues = new ContentValues();
        contentValues.put("current_date", currentDate);
        long rowId = sqLiteDatabase.insert("note", null, contentValues);
        return (rowId >= 0)? true : false;
    }
}
