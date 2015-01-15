package com.example.takahiro_tsuno.notepad;

import android.content.Context;
import java.util.List;

public class NoteModel {

    public static NoteDao noteDao;

    public NoteModel(Context context) {
        noteDao = new NoteDao(context);
    }

    public static void createTable() {
        noteDao.createTable();
    }

    public static void dropTable() {
        noteDao.dropTable();
    }

    public List<Note> getList() {
        return noteDao.getList();
    }

    public Note add(Note note) {
        return noteDao.add(note);
    }

    public boolean delete(Note note){
        return noteDao.delete(note);
    }

    public Note update(Note note) {
        return noteDao.update(note);
    }
}
