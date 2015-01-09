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
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            Note note = new Note(id, title, description);

            noteList.add(note);
        }

        //戻す
        return noteList;
    }

    public boolean add(String title, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        long rowId = sqLiteDatabase.insert("note", null, contentValues);
        return (rowId >= 0)? true : false;
    }

    public boolean delete(int id){
        String idString = String.valueOf(id);
        String params[] = {idString};
        int result = sqLiteDatabase.delete("note", "_id = ?", params);
        return (result == 1)? true : false;
    }
    
    public boolean delete(Note note){
        return delete(note.getId());
    }

    public boolean update(int id, String title, String description){
        String idString = String.valueOf(id);
        String params[] = {idString};

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        int result = sqLiteDatabase.update("note", contentValues, "_id = ?", params);
        return (result == 1)? true : false;
    }
}
