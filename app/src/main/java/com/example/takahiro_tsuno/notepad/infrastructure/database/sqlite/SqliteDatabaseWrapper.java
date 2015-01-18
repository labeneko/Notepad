package com.example.takahiro_tsuno.notepad.infrastructure.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.takahiro_tsuno.notepad.infrastructure.database.Column;
import com.example.takahiro_tsuno.notepad.infrastructure.database.Table;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.Arrays;

public class SqliteDatabaseWrapper {

    private SQLiteDatabase database;

    public SqliteDatabaseWrapper(SQLiteDatabase database) {
        this.database = database;
    }

    public void create(Table table) {
        String columnString = Joiner.on(",").join(Lists.transform(table.getColumns(), new Function<Column, String>() {
            @Override
            public String apply(final Column input) {
                return String.format("%s %s", input.getColumnName(), input.getAttribute());
            }
        }));
        database.execSQL(String.format("CREATE %s (%s)", table.getTableName(), columnString));
    }

    public int count(Table table) {
        Cursor cr = database.rawQuery(String.format("SELECT * FROM %s", table.getTableName()), null);
        int result = cr.getCount();
        cr.close();
        return result;
    }

    public int count(Table table, String where, String... values) {
        Cursor cr = database.rawQuery(String.format("SELECT * FROM %s WHERE %s", table.getTableName(), where), values);
        int result = cr.getCount();
        cr.close();
        return result;
    }

    public boolean contains(Table table, long id) {
        return find(table, id) != null;
    }

    public Cursor find(Table table, long id) {
        return database.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", table.getTableName(), Table.ID.getColumnName()), new String[] {String.valueOf(id)});
    }

    public Cursor findAll(Table table) {
        return database.rawQuery(String.format("SELECT * FROM %s", table.getTableName()), null);
    }

    public Cursor findAll(Table table, String where, Object... values) {
        return database.rawQuery(String.format("SELECT * FROM %s WHERE %s", table.getTableName(), where), toStringValues(values));
    }

    public long insert(Table table, ContentValues contentValues) {
        return database.insert(table.getTableName(), null, contentValues);
    }

    public long update(Table table, ContentValues contentValues, String where, Object... values) {
        return database.update(table.getTableName(), contentValues, where, toStringValues(values));
    }

    public long delete(Table table, String where, Object... values) {
        return database.delete(table.getTableName(), where, toStringValues(values));
    }

    private static String[] toStringValues(Object[] values) {
        return Lists.transform(Arrays.asList(values), new Function<Object, String>() {
            @Override
            public String apply(final Object input) {
                return input.toString();
            }
        }).toArray(new String[values.length]);
    }
}
