package com.example.takahiro_tsuno.notepad.infrastructure.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database {

    private static final String TAG = Database.class.getSimpleName();
    private static DatabaseHelper databaseHelper;
    private static boolean initialized = false;

    public static synchronized void initialize(Context context) {
        if(initialized) {
            Log.w(TAG, "database already initialized.");
            return;
        }
        databaseHelper = new DatabaseHelper(new DatabaseConfig(context));
        open();
        initialized = true;
        Log.i(TAG, "database initialized successfully.");
    }

    public static synchronized void dispose() {
        close();
        databaseHelper = null;
        initialized = false;
        Log.i(TAG, "database disposed.");
    }

    public static SQLiteDatabase open() {
        return databaseHelper.getWritableDatabase();
    }

    private static void close() {
        databaseHelper.close();
    }
}
