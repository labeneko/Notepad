package com.example.takahiro_tsuno.notepad.infrastructure.database;

import java.util.Date;

public class AbstractEntity implements Entity {

    public static final int EMPTY_ID = -1;
    private long id;
    private Date createdAt;
    private Date updatedAt;

    public AbstractEntity() {
        this(EMPTY_ID, null, null);
    }

    public AbstractEntity(Date createdAt, Date updatedAt) {
        this(EMPTY_ID, createdAt, updatedAt);
    }

    public AbstractEntity(long id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
