package com.example.takahiro_tsuno.notepad.application;

import com.example.takahiro_tsuno.notepad.infrastructure.database.Database;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Database.initialize(this);
    }

    @Override
    public void onTerminate() {
        Database.dispose();
        super.onTerminate();
    }
}
