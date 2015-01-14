package com.example.takahiro_tsuno.notepad;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteModel {

    // 本当はDaoを作ってそちらで管理すべき
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    NotepadDbHelper notepadDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public NoteModel(Context context) {
        notepadDbHelper = new NotepadDbHelper(context);
        //DB
        sqLiteDatabase = notepadDbHelper.getReadableDatabase();
    }

    public static void createTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement," +
                COLUMN_TITLE + " text," +
                COLUMN_DESCRIPTION + " text)");
    }

    public static void dropTable(SQLiteDatabase sqLiteDatabase) {
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

    public boolean add(String title, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return (rowId >= 0)? true : false;
    }

    public boolean delete(int id){
        String idString = String.valueOf(id);
        String params[] = {idString};
        int result = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + " = ?", params);
        return (result == 1)? true : false;
    }
    
    public boolean delete(Note note){
        return delete(note.getId());
    }

    public boolean update(int id, String title, String description){
        String idString = String.valueOf(id);
        String params[] = {idString};

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        int result = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", params);
        return (result == 1)? true : false;
    }
}
