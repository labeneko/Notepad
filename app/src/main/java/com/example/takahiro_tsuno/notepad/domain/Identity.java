package com.example.takahiro_tsuno.notepad.domain;

public interface Identity<T> extends ValueObject {

    public T getValue();
}
