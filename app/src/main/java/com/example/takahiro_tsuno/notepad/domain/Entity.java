package com.example.takahiro_tsuno.notepad.domain;

import java.io.Serializable;

public interface Entity<I extends Identity, E extends Entity<I, E>> extends Cloneable, Serializable {

    public I getIdentity();

    public boolean equals(Object that);

    public int hashCode();

    public E clone();
}
