package com.example.takahiro_tsuno.notepad.domain.note;

import java.util.List;

public interface NoteRepository {

    public Note find(NoteIdentity noteIdentity);

    public List<Note> findAll();

    public Note add(Note note);

    public Note update(Note note);

    /**
     * 追加または更新を行う(repositoryに存在しているかどうかで挙動が変わる)
     */
    public Note save(Note note);

    public boolean remove(NoteIdentity noteIdentity);

    public boolean remove(Note note);

    public boolean contains(Note note);
}
