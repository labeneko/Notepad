package com.example.takahiro_tsuno.notepad;


import java.io.Serializable;

public class Note implements Serializable{
    private long id = -1;
    private String title;
    private String description;

    public Note(String title, String description){
        this.title = title;
        this.description = description;
    }

    public Note(long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
