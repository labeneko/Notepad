package com.example.takahiro_tsuno.notepad.infrastructure.database.dao;

import com.example.takahiro_tsuno.notepad.infrastructure.database.Entity;

import java.util.List;

public interface Dao<E extends Entity> {

    public long count();

    public boolean contains(long id);

    public E find(long id);

    public List<E> findAll();

    public E insert(E item);

    public E update(E item);

    public E updateOrInsert(E item);

    public long remove(long id);

    public long remove(E item);
}
