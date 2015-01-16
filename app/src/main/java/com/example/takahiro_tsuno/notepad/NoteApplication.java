package com.example.takahiro_tsuno.notepad;


import android.app.Application;
import android.content.Context;

public class NoteApplication extends Application {

    private NoteModel noteModel;

    // 非同期で同時に呼び出されても問題ないようにsynchronizedを付ける
    public synchronized NoteModel getNoteModel() {
        if(noteModel == null) {
            // TODO: NoteModelにActivityを渡した時とapplicationContextを渡した時との違いを検証
            noteModel = new NoteModel(getApplicationContext());
        }
        return noteModel;
    }
}
