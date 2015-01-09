package com.example.takahiro_tsuno.notepad;


import java.io.Serializable;

public class Note implements Serializable{
    private int id;
    private String currentDate;

    public Note(int id, String currentDate){
        this.id = id;
        this.currentDate = currentDate;
    }

    public int getId(){
        return id;
    }

    public String getCurrentDate(){
        return currentDate;
    }
}
