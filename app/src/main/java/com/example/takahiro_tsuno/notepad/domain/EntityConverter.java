package com.example.takahiro_tsuno.notepad.domain;

public interface EntityConverter<DOMAIN extends Entity<?, ?>, DATABASE extends com.example.takahiro_tsuno.notepad.infrastructure.database.Entity> {

    public DOMAIN toDomainEntity(DATABASE dbEntity);

    public DATABASE toDatabaseEntity(DOMAIN domainEntity);
}
