package com.example.takahiro_tsuno.notepad;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    public static NotepadDbHelper notepadDbHelper;
    public static SQLiteDatabase sqLiteDatabase;

    public NoteDao(Context context) {
        notepadDbHelper = new NotepadDbHelper(context);
        //DB
        sqLiteDatabase = notepadDbHelper.getReadableDatabase();
    }

    public static void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement," +
                COLUMN_TITLE + " text," +
                COLUMN_DESCRIPTION + " text)");
    }

    public static void dropTable() {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public List<Note> getList(){
        List<Note> noteList = new ArrayList<Note>();

        Cursor cursor = sqLiteDatabase.query(
                TABLE_NAME,
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

            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

            Note note = new Note(id, title, description);

            noteList.add(note);
        }

        //戻す
        return noteList;
    }

    public Note add(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, note.getDescription());
        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(rowId > 0){
            note.setId(rowId);
            return note;
        }
        return null;
    }
    
    public boolean delete(Note note) {
        String idString = String.valueOf(note.getId());
        String params[] = {idString};
        int result = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + " = ?", params);
        return (result == 1)? true : false;
    }

    public Note update(Note note){
        String idString = String.valueOf(note.getId());
        String params[] = {idString};

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, note.getDescription());
        int affectedRows = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", params);
        if(affectedRows == 1) {
            return note;
        }
        return null;
    }
}
