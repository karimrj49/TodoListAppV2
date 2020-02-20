package com.example.todolist1.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "status_table")
public class Status {

    @PrimaryKey(autoGenerate = true)
    private int statusid ;
    private String title;

    public Status(String title) {
        this.title = title;
    }

    public int getStatusid() {
        return statusid;
    }

    public String getTitle() {
        return title;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
