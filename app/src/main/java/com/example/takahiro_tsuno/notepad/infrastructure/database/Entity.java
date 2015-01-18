package com.example.takahiro_tsuno.notepad.infrastructure.database;

import java.util.Date;

public interface Entity {

    public long getId();

    public Date getCreatedAt();

    public Date getUpdatedAt();
}
