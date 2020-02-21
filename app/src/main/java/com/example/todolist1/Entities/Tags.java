package com.example.todolist1.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tags_table")
public class Tags {


    @PrimaryKey(autoGenerate = true)
    private int id;

    public Tags(String tag_name) {
        this.tag_name = tag_name;
    }

    private String tag_name;

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
