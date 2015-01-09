package com.example.takahiro_tsuno.notepad;


import java.io.Serializable;

public class Note implements Serializable{
    private int id;
    private String title;
    private String description;

    public Note(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }
}
